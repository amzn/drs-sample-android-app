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

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_CONNECTION;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_MALFORMED_URL;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_MESSAGE;
import static com.amazon.drs.drssampleandroidapp.Constants.HTTP_POST;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_JSON;
import static com.amazon.drs.drssampleandroidapp.Constants.LWA_AUTH_CODE;
import static com.amazon.drs.drssampleandroidapp.Constants.LWA_CLIENT_ID;
import static com.amazon.drs.drssampleandroidapp.Constants.LWA_CODE;
import static com.amazon.drs.drssampleandroidapp.Constants.LWA_CODE_VERIFIER;
import static com.amazon.drs.drssampleandroidapp.Constants.LWA_GRANT_TYPE;
import static com.amazon.drs.drssampleandroidapp.Constants.LWA_REDIRECT_URI;
import static com.amazon.drs.drssampleandroidapp.Constants.LWA_ENDPOINT;
import static com.amazon.drs.drssampleandroidapp.Constants.UTF8_ENCODING;
import static com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse.ACCESS_TOKEN;
import static com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse.REFRESH_TOKEN;

/**
 * This class converts customer's LWA access code to Access token and refresh token.
 * Refresh token to access token is also here. This is for illustrative purposes only.
 */
class TokenGenerator {

    private static final String TAG = TokenGenerator.class.getName();

    private String mResponseMessage;
    private String sRefresh_token;
    private String mLwaAccessCode;
    private final String mClientId;
    private final String mRedirectUri;

    /**
     * Refers to the instance of activity from which the Identity handler was invoked.
     * This is used to delegate updates to the user.
     */
    private final MainActivity mMainActivity;

    /**
     * Constructs a new token generator that is used to retrieve the Access Token and Refresh Token.
     * This constructor is used to get the refresh token during the setup.
     *
     * @param lwaAccessCode The access code returned during the authorize lwa call.
     * @param mainActivity  instance of activity from which Identity Handler was invoked.
     * @param clientId      The client ID returned from LWA.
     * @param redirectUri   The redirect URI returned from LWA.
     */
    TokenGenerator(final String lwaAccessCode, final MainActivity mainActivity,
                   final String clientId, final String redirectUri) {
        mMainActivity = mainActivity;
        mLwaAccessCode = lwaAccessCode;
        mClientId = clientId;
        mRedirectUri = redirectUri;
    }

    /**
     * Constructs a new token generator that is used to retrieve the Access Token and Refresh Token.
     * This constructor is used to get the Access token during the setup.
     *
     * @param mainActivity  instance of activity from which Identity Handler was invoked.
     * @param clientId      The client ID returned from LWA.
     * @param refresh_token the refresh token supplied from LWA.
     */
    TokenGenerator(final MainActivity mainActivity, final String clientId,
                   final String refresh_token) {
        this(null, mainActivity, clientId, null);
        sRefresh_token = refresh_token;
    }

    /**
     * Requests to generate a new Access Token.
     * Utilises the refresh token already passed into the token Generator.
     */
    void getNewAccessToken() {
        new RequestTask().execute(true);
    }

    /**
     * Requests to generate the Access token and the request token.
     * Uses the code that is received from LWA authorize call.
     */
    void requestAccessAndRefreshTokens() {
        new RequestTask().execute(false);
    }

    /**
     * retrieves the string object of the key.
     *
     * @param jsonKey Key used to retrieve the Json Object.
     * @return String value of the Json object from the response message.
     */
    private String getStringValue(final String jsonKey) {
        try {
            final JSONObject jsonObject = new JSONObject(mResponseMessage);
            if (jsonObject.has(jsonKey)) {
                return jsonObject.getString(jsonKey);
            }
        } catch (final JSONException e) {
            Log.e(TAG, INVALID_JSON + jsonKey, e);
        }
        return null;
    }

    /**
     * Updates the main activity with the access token.
     */
    private void getAccessToken() {
        final String accessToken = getStringValue(ACCESS_TOKEN);
        mMainActivity.updateTokenTextView(accessToken);
    }

    /**
     * Updates the main activity with the refresh token.
     */
    private void getRefreshToken() {
        final String refreshToken = getStringValue(REFRESH_TOKEN);
        if (!(refreshToken != null && refreshToken.isEmpty())) {
            sRefresh_token = refreshToken;
        }
        mMainActivity.updateRefreshTokenTextView(refreshToken);
    }

    /**
     * Asynchronous task to perform request to hosts.
     */
    private class RequestTask extends AsyncTask<Boolean, Void, Void> {

        /**
         * Builds URL query from the ContentValue.
         *
         * @param params key value pair for the POST request.
         * @return String object for the body of the request.
         * @throws UnsupportedEncodingException If the encoding is not supported.
         */
        private String getQuery(final ContentValues params) throws UnsupportedEncodingException {
            final StringBuilder result = new StringBuilder();
            boolean first = true;
            for (final Map.Entry<String, Object> pair : params.valueSet()) {
                if (first) {
                    first = false;
                } else {
                    result.append("&");
                }
                if (pair.getValue() != null && pair.getKey() != null) {
                    result.append(URLEncoder.encode(pair.getKey(), UTF8_ENCODING));
                    result.append("=");
                    result.append(URLEncoder.encode(pair.getValue().toString(), UTF8_ENCODING));
                }
            }
            return result.toString();
        }

        /**
         * Gets a string from the input stream.
         *
         * @param inputStream Stream received from the UrlConnection.
         * @return Response Message.
         * @throws IOException If any internal errors are encountered inside the client while
         *                     attempting to make the request to read the contents
         *                     of the stream and parse it.
         *                     For example the HOST_FILE's input stream is no longer valid.
         * @hide
         */
        private String getStringFromStream(final InputStream inputStream) throws IOException {
            return DrsUtils.getStringFromStream(inputStream);
        }

        /**
         * Checks to see if there are any errors.
         *
         * @param urlConnection standard HttpURLConnection object
         * @return True on error. False on no errors.
         * @throws IOException if can not read from stream.
         */
        private boolean isErrorStream(final HttpURLConnection urlConnection) throws IOException {
            final InputStream errorStream = urlConnection.getErrorStream();
            if (errorStream != null) {
                try {
                    final String responseString = getStringFromStream(errorStream);
                    Log.e(TAG, ERROR_MESSAGE + responseString);
                    mMainActivity.updateNotificationView(responseString);
                    return true;
                } finally {
                    errorStream.close();
                }
            }
            return false;
        }

        /**
         * Build the HttpURLConnection.
         *
         * @param isNewAccessToken true if the request if from code and false
         *                         if request is made from Refresh token.
         * @return HttpUrlConnection object which connects to LWA service.
         * @throws IOException
         */
        private HttpURLConnection getUrlConnection(final boolean isNewAccessToken)
                throws IOException {
            final URL hostUrl = new URL(LWA_ENDPOINT);
            final HttpURLConnection urlConnection = (HttpURLConnection) hostUrl.openConnection();

            urlConnection.setRequestMethod(HTTP_POST);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            final ContentValues params = new ContentValues();
            if (isNewAccessToken) {
                params.put(LWA_GRANT_TYPE, REFRESH_TOKEN);
                params.put(REFRESH_TOKEN, sRefresh_token);
                params.put(LWA_CLIENT_ID, mClientId);
            } else {
                params.put(LWA_GRANT_TYPE, LWA_AUTH_CODE);
                params.put(LWA_CODE, mLwaAccessCode);
                params.put(LWA_REDIRECT_URI, mRedirectUri);
                params.put(LWA_CLIENT_ID, mClientId);
                final CodeChallengeGenerator codeChallengeGenerator =
                        CodeChallengeGenerator.getInstance();
                params.put(LWA_CODE_VERIFIER,
                        codeChallengeGenerator.getCodeVerifier());
            }
            final OutputStream outputStream = urlConnection.getOutputStream();
            try {
                final BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(outputStream, UTF8_ENCODING));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                outputStream.close();
                urlConnection.connect();
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            return urlConnection;
        }

        /**
         * Makes an asynchronous HttpUrlConnection to the DRS service.
         *
         * @return null response when the request is complete.
         * @see RequestTaskParameters
         */
        @Override
        protected Void doInBackground(final Boolean... flags) {
            final boolean isNewAccessToken = flags[0];
            try {
                final HttpURLConnection urlConnection = getUrlConnection(isNewAccessToken);
                if (isErrorStream(urlConnection)) {
                    return null;
                }
                final InputStream inputStream = urlConnection.getInputStream();
                if (inputStream != null) {
                    try {
                        mResponseMessage = getStringFromStream(inputStream);
                        mMainActivity.updateNotificationView(mResponseMessage);
                        getAccessToken();
                        getRefreshToken();
                    } finally {
                        inputStream.close();
                    }
                }
            } catch (final MalformedURLException e) {
                Log.e(TAG, ERROR_MALFORMED_URL, e);
            } catch (final IOException e) {
                Log.e(TAG, ERROR_CONNECTION, e);
            }
            return null;
        }
    }
}
