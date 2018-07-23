/*
 * Copyright 2016-2018 Amazon.com, Inc. or its affiliates.  All Rights Reserved.
 *
 * Except as set forth below, this software is licensed under the Amazon Software License (the "License").  You may not use this software except in compliance with the License.
 * A copy of the License is located at
 *  http://aws.amazon.com/asl/
 * and is also copied below.
 *
 * This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 * NOTICE REGARDING LOGIN WITH AMAZON
 *
 * This software includes certain Login with Amazon software (the "LWA Software") and requires the LWA Software to function.  The LWA Software is licensed as "Program Materials" under the Program Materials License Agreement of the Amazon Mobile App Distribution program, which is available at https://developer.amazon.com/sdk/pml.html.  See the Program Materials License Agreement for the specific language governing permissions and limitations applicable to the LWA Software.
 * The LWA Software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * FULL TEXT OF AMAZON SOFTWARE LICENSE
 *
 * Amazon Software License
 * 1. Definitions
 * “Licensor” means any person or entity that distributes its Work.
 * “Software” means the original work of authorship made available under this License.
 * “Work” means the Software and any additions to or derivative works of the Software that are made available under this License.
 * The terms “reproduce,” “reproduction,” “derivative works,” and “distribution” have the meaning as provided under U.S. copyright law; provided, however, that for the purposes of this License, derivative works shall not include works that remain separable from, or merely link (or bind by name) to the interfaces of, the Work.
 * Works, including the Software, are “made available” under this License by including in or with the Work either (a) a copyright notice referencing the applicability of this License to the Work, or (b) a copy of this License.
 * 2. License Grants
 * 2.1 Copyright Grant. Subject to the terms and conditions of this License, each Licensor grants to you a perpetual, worldwide, non-exclusive, royalty-free, copyright license to reproduce, prepare derivative works of, publicly display, publicly perform, sublicense and distribute its Work and any resulting derivative works in any form.
 * 2.2 Patent Grant. Subject to the terms and conditions of this License, each Licensor grants to you a perpetual, worldwide, non-exclusive, royalty-free patent license to make, have made, use, sell, offer for sale, import, and otherwise transfer its Work, in whole or in part. The foregoing license applies only to the patent claims licensable by Licensor that would be infringed by Licensor’s Work (or portion thereof) individually and excluding any combinations with any other materials or technology.
 * 3. Limitations
 * 3.1 Redistribution. You may reproduce or distribute the Work only if (a) you do so under this License, (b) you include a complete copy of this License with your distribution, and (c) you retain without modification any copyright, patent, trademark, or attribution notices that are present in the Work.
 * 3.2 Derivative Works. You may specify that additional or different terms apply to the use, reproduction, and distribution of your derivative works of the Work (“Your Terms”) only if (a) Your Terms provide that the use limitation in Section 3.3 applies to your derivative works, and (b) you identify the specific derivative works that are subject to Your Terms. Notwithstanding Your Terms, this License (including the redistribution requirements in Section 3.1) will continue to apply to the Work itself.
 * 3.3 Use Limitation. The Work and any derivative works thereof only may be used or intended for use with the web services, computing platforms or applications provided by Amazon.com, Inc. or its affiliates, including Amazon Web Services, Inc.
 * 3.4 Patent Claims. If you bring or threaten to bring a patent claim against any Licensor (including any claim, cross-claim or counterclaim in a lawsuit) to enforce any patents that you allege are infringed by any Work, then your rights under this License from such Licensor (including the grants in Sections 2.1 and 2.2) will terminate immediately.
 * 3.5 Trademarks. This License does not grant any rights to use any Licensor’s or its affiliates’ names, logos, or trademarks, except as necessary to reproduce the notices described in this License.
 * 3.6 Termination. If you violate any term of this License, then your rights under this License (including the grants in Sections 2.1 and 2.2) will terminate immediately.
 * 4. Disclaimer of Warranty.
 * THE WORK IS PROVIDED “AS IS” WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WARRANTIES OR CONDITIONS OF M ERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE OR NON-INFRINGEMENT. YOU BEAR THE RISK OF UNDERTAKING ANY ACTIVITIES UNDER THIS LICENSE. SOME STATES’ CONSUMER LAWS DO NOT ALLOW EXCLUSION OF AN IMPLIED WARRANTY, SO THIS DISCLAIMER MAY NOT APPLY TO YOU.
 * 5. Limitation of Liability.
 * EXCEPT AS PROHIBITED BY APPLICABLE LAW, IN NO EVENT AND UNDER NO LEGAL THEORY, WHETHER IN TORT (INCLUDING NEGLIGENCE), CONTRACT, OR OTHERWISE SHALL ANY LICENSOR BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT OF OR RELATED TO THIS LICENSE, THE USE OR INABILITY TO USE THE WORK (INCLUDING BUT NOT LIMITED TO LOSS OF GOODWILL, BUSINESS INTERRUPTION, LOST PROFITS OR DATA, COMPUTER FAILURE OR MALFUNCTION, OR ANY OTHER COMM ERCIAL DAMAGES OR LOSSES), EVEN IF THE LICENSOR HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * Effective Date – April 18, 2008 © 2008 Amazon.com, Inc. or its affiliates. All rights reserved.
 */

package com.amazon.drs.drssampleandroidapp;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.amazon.drs.drssampleandroidapp.Constants.ALGORITHM_SHA_256;
import static com.amazon.drs.drssampleandroidapp.Constants.PLAIN;
import static com.amazon.drs.drssampleandroidapp.Constants.SHA_256;
import static com.amazon.drs.drssampleandroidapp.Constants.UTF8_ENCODING;

/**
 * This class is used for generating a secure random SPOP string.
 * This creates a String that is used in the identity handler during authorization.
 *
 * @see IdentityHandler
 * The generated private key is used in verification in the TokenGenerator.
 * @see TokenGenerator
 */
class CodeChallengeGenerator {


    private static final String LOG_TAG = CodeChallengeGenerator.class.getName();

    private static CodeChallengeGenerator instance;
    private String mCodeVerifier;

    /**
     * Singleton class. Not to be instantiated externally.
     */
    private CodeChallengeGenerator() {
    }

    /**
     * @return instance of the CodeChallengeGenerator.
     */
    static CodeChallengeGenerator getInstance() {
        if (instance == null) {
            instance = new CodeChallengeGenerator();
        }
        return instance;
    }

    /**
     * To be used in the Token generator.
     *
     * @return private key used for encryption.
     */
    String getCodeVerifier() {
        if (mCodeVerifier == null || mCodeVerifier.length() == 0) {
            mCodeVerifier = generateCodeVerifier();
        }
        return mCodeVerifier;
    }

    /**
     * Checks and tries to perform the SHA256 encryption else returns plan string.
     *
     * @param codeVerifier        Code Verifier the private key used for SPOP.
     * @param codeChallengeMethod The Code challenge method to use.
     * @return encrypted SHA256 string if encryption possible else plain string.
     * @throws NoSuchAlgorithmException     When the device does not support SHA 256 encryption.
     * @throws UnsupportedEncodingException when the device does not support UTF-8 encoding.
     */
    String generateCodeChallenge(final String codeVerifier, final String codeChallengeMethod)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final String codeChallenge;
        if (SHA_256.equalsIgnoreCase(codeChallengeMethod)) {
            codeChallenge = base64UrlEncode(
                    MessageDigest.getInstance(ALGORITHM_SHA_256).digest(
                            codeVerifier.getBytes(UTF8_ENCODING)));
            return codeChallenge;
        } else if (PLAIN.equalsIgnoreCase(codeChallengeMethod)) {
            return codeVerifier;
        }
        throw new NoSuchAlgorithmException();
    }

    /**
     * Generate random Code verifier base 64 encoded.
     *
     * @return Base64 URL encoded string value (Code Verifier).
     */
    private String generateCodeVerifier() {
        final byte[] randomOctetSequence = generateRandomOctetSequence();
        return base64UrlEncode(randomOctetSequence);
    }

    /**
     * As per Proof Key/SPOP protocol Version 10.
     *
     * @return a random 32 sized octet sequence from allowed range.
     */
    /*
     * There is a known security vulnerability in SecureRandom :
     * http://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html
     * However it surfaces when SecureRandom is incorrectly seeded. Since we are not
     * seeding the SecureRandom, we should be good.
     */
    @SuppressLint("TrulyRandom")
    private byte[] generateRandomOctetSequence() {
        final SecureRandom random = new SecureRandom();
        final byte[] octetSequence = new byte[32];
        random.nextBytes(octetSequence);
        return octetSequence;
    }

    /**
     * This method is borrowed from the SPOP protocol spec version 10 here:
     * http://datatracker.ietf.org/doc/draft-ietf-oauth-spop/?include_text=1
     * Fortunately Android library provides this implementation of Base64 encoding
     * with custom flags. We use that instead.
     *
     * @param arg the string to convert.
     * @return base64 URL encoded string value as specified by spec.
     */
    private String base64UrlEncode(final byte[] arg) {
        return Base64.encodeToString(arg, Base64.NO_PADDING | Base64.URL_SAFE | Base64.NO_WRAP);
    }
}
