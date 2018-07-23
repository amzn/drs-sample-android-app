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

import android.app.Activity;
import android.util.Log;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.Listener;
import com.amazon.identity.auth.device.api.authorization.AuthCancellation;
import com.amazon.identity.auth.device.api.authorization.AuthorizeListener;
import com.amazon.identity.auth.device.api.authorization.AuthorizeRequest;
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult;
import com.amazon.identity.auth.device.api.authorization.Scope;
import com.amazon.identity.auth.device.api.authorization.ScopeFactory;
import com.amazon.identity.auth.device.api.workflow.RequestContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import static com.amazon.drs.drssampleandroidapp.Constants.AMZ_AUTH_DEVICE_MODEL;
import static com.amazon.drs.drssampleandroidapp.Constants.AMZ_AUTH_SCOPE_DASH_REPLENISH;
import static com.amazon.drs.drssampleandroidapp.Constants.AMZ_AUTH_SERIAL;
import static com.amazon.drs.drssampleandroidapp.Constants.AMZ_AUTH_TEST_DEVICE;
import static com.amazon.drs.drssampleandroidapp.Constants.AMZ_INCLUDE_NON_LIVE_DEVICES;
import static com.amazon.drs.drssampleandroidapp.Constants.CANCEL_AUTHORIZATION;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_AUTHORIZATION;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_LOGOUT;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_WRONG_ACTIVITY;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_CODE_CHALLENGE_METHOD;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_ENCODING;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_JSON;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_LOGIN;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_LOGIN_REQUEST;
import static com.amazon.drs.drssampleandroidapp.Constants.SHA_256;
import static com.amazon.drs.drssampleandroidapp.Constants.SUCCESS_LOGIN;
import static com.amazon.drs.drssampleandroidapp.Constants.SUCCESS_LOGOUT;
import static com.amazon.drs.drssampleandroidapp.Constants.UTF8_ENCODING;

/**
 * Utilities for interacting with LWA.
 * This class creates a new authorization for the specified OAuth application,
 * only if an authorization for that application does not already exist for the user.
 * The URL includes the 20 character client ID for the OAuth app that is requesting the token.
 * It returns the user’s existing authorization for the application if one is present. Otherwise,
 * it creates and returns a new one.
 */
class IdentityHandler {

    private static final String TAG = IdentityHandler.class.getName();

    /**
     * LWA RequestContext.
     */
    private final RequestContext mRequestContext;

    /**
     * Wrapper for AuthorizationManager.
     */
    private final AuthorizationManagerWrapper mAuthorizationManagerWrapper;

    /**
     * Refers to the instance of activity from which the IdentityHandler handler is invoked.
     * This is used to delegate updates to the user.
     */
    private final MainActivity mMainActivity;

    /**
     * Code challenge generator. Test purpose only.
     */
    private final CodeChallengeGenerator mCodeChallengeGenerator;

    /**
     * Constructs a new IdentityHandler to make requests to the LWA Sdk.
     * <p>
     * Some calls made using this new IdentityHandler object are non blocking, and will update
     * the main activity when the call completes.
     * </p>
     *
     * @param activity MainActivity used for initialization.
     */
    IdentityHandler(final Activity activity,
                    final CodeChallengeGenerator codeChallengeGenerator,
                    final AuthorizationManagerWrapper authorizationManagerWrapper) {
        if (activity instanceof MainActivity) {
            mRequestContext = RequestContext.create(activity);
            mMainActivity = (MainActivity) activity;
            mCodeChallengeGenerator = codeChallengeGenerator;
            registerListeners();
            mAuthorizationManagerWrapper = authorizationManagerWrapper;
        } else {
            mRequestContext = null;
            mMainActivity = null;
            mCodeChallengeGenerator = null;
            mAuthorizationManagerWrapper = null;
            Log.e(TAG, ERROR_WRONG_ACTIVITY);
        }
    }

    /**
     * Resume the RequestContext.
     */
    void resumeContext() {
        if (mRequestContext != null) {
            mRequestContext.onResume();
        }
    }

    /**
     * Set LWA login listeners (onSuccess, onError, onCancel).
     */
    private void registerListeners() {
        mRequestContext.registerListener(new LoginListener());
    }

    /**
     * Returns the scope required for access token.
     *
     * @return the necessary scopes.
     */
    private String getAccessScopes() {
        return AMZ_AUTH_SCOPE_DASH_REPLENISH;
    }

    /**
     * Prompts the user to login and authorize the application.
     */
    void login() {
        final AuthorizeRequest request = getLoginRequest();
        if (request != null) {
            mAuthorizationManagerWrapper.authorize(request);
        } else {
            mMainActivity.updateNotificationView(INVALID_LOGIN_REQUEST);
            Log.e(TAG, INVALID_LOGIN_REQUEST);
        }

    }

    /**
     * Build login request.
     *
     * @return AuthorizeRequest or null if there was some error.
     */
    private AuthorizeRequest getLoginRequest() {
        final AuthorizeRequest request;
        try {
            request = new AuthorizeRequest.Builder(mRequestContext)
                    .addScopes(getScope())
                    .forGrantType(AuthorizeRequest.GrantType.AUTHORIZATION_CODE)
                    .withProofKeyParameters(mCodeChallengeGenerator.generateCodeChallenge(
                            mCodeChallengeGenerator.getCodeVerifier(),
                            SHA_256), SHA_256)
                    .build();
            return request;
        } catch (final NoSuchAlgorithmException e) {
            mMainActivity.updateTokenTextView(INVALID_CODE_CHALLENGE_METHOD);
            Log.e(TAG, INVALID_CODE_CHALLENGE_METHOD, e);
        } catch (final UnsupportedEncodingException e) {
            mMainActivity.updateTokenTextView(INVALID_ENCODING);
            Log.e(TAG, INVALID_ENCODING, e);
        }
        return null;
    }

    /**
     * Build LWA login scope.
     *
     * @return Login scope.
     */
    private Scope getScope() {
        final JSONObject scopeData = new JSONObject();
        final String modelId;
        final String serialID;
        final boolean isTest;
        final boolean includeNonLiveDevices;
        try {
            modelId = URLEncoder.encode(mMainActivity.getModelId(), UTF8_ENCODING);
            serialID =
                    URLEncoder.encode(mMainActivity.getDSN(), UTF8_ENCODING);
            isTest = mMainActivity.isTestEnabled();
            includeNonLiveDevices = mMainActivity.includeNonLiveDevices();
            scopeData.accumulate(AMZ_AUTH_DEVICE_MODEL, modelId);
            scopeData.accumulate(AMZ_AUTH_SERIAL, serialID);
            scopeData.accumulate(AMZ_AUTH_TEST_DEVICE, isTest);
            scopeData.accumulate(AMZ_INCLUDE_NON_LIVE_DEVICES, includeNonLiveDevices);
        } catch (final UnsupportedEncodingException e) {
            mMainActivity.updateTokenTextView(INVALID_ENCODING);
            Log.e(TAG, INVALID_ENCODING, e);
        } catch (final JSONException e) {
            mMainActivity.updateTokenTextView(INVALID_JSON);
            Log.e(TAG, INVALID_ENCODING, e);
        }
        return ScopeFactory.scopeNamed(getAccessScopes(), scopeData);
    }

    /**
     * Resets the current state of authorization manager. Next request for retrieving access token
     * will prompt the user for credentials.
     */
    void logout() {
        mAuthorizationManagerWrapper.signOut(mMainActivity, new LogoutListener());
    }

    /**
     * Requests for a new Access token using the refresh token.
     *
     * @param refreshToken Refresh token retrieved from LWA.
     */
    void getNewAccessToken(final String refreshToken) {
        final TokenGenerator tokenGenerator = new TokenGenerator(mMainActivity,
                mMainActivity.getClientID(), refreshToken);
        tokenGenerator.getNewAccessToken();
    }

    /**
     * Listener for Logout events.
     */
    private class LogoutListener implements Listener<Void, AuthError> {
        /**
         * Logout was completed successfully.
         * {@inheritDoc}
         */
        @Override
        public void onSuccess(final Void aVoid) {
            Log.i(TAG, SUCCESS_LOGOUT);
            if (mMainActivity != null) {
                mMainActivity.updateNotificationView("Logged out successfully");
            }
        }

        /**
         * There was an error during the attempt to authorize the application.
         * {@inheritDoc}
         */
        @Override
        public void onError(final AuthError authError) {
            Log.e(TAG, ERROR_LOGOUT, authError);
        }
    }

    /**
     * Listener for authorization events.
     */
    private class LoginListener extends AuthorizeListener {

        /**
         * Authorization was completed successfully.
         * {@inheritDoc}
         */
        @Override
        public void onSuccess(final AuthorizeResult authorizeResult) {
            if (authorizeResult != null) {
                Log.i(TAG, SUCCESS_LOGIN);
                final String code = authorizeResult.getAuthorizationCode();
                final String clientId = authorizeResult.getClientId();
                final String redirectUri = authorizeResult.getRedirectURI();
                final TokenGenerator tokenGenerator =
                        new TokenGenerator(code, mMainActivity, clientId, redirectUri);
                tokenGenerator.requestAccessAndRefreshTokens();
                if (mMainActivity != null) {
                    mMainActivity.setClientID(clientId);
                }
            } else {
                Log.e(TAG, INVALID_LOGIN);
            }
        }

        /**
         * There was an error during the attempt to authorize the application.
         * {@inheritDoc}
         */
        @Override
        public void onError(final AuthError authError) {
            Log.e(TAG, ERROR_AUTHORIZATION, authError);
        }

        /**
         * Authorization was cancelled before it could be completed.
         * {@inheritDoc}
         */
        @Override
        public void onCancel(final AuthCancellation authCancellation) {
            Log.i(TAG, CANCEL_AUTHORIZATION);
        }
    }
}
