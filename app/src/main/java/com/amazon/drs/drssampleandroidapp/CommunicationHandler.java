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

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.amazon.drs.drssampleandroidapp.Constants.BEARER;
import static com.amazon.drs.drssampleandroidapp.Constants.CONTENT_TYPE_KEY;
import static com.amazon.drs.drssampleandroidapp.Constants.CONTENT_TYPE_VALUE_JSON;
import static com.amazon.drs.drssampleandroidapp.Constants.DEVICE_STATUS;
import static com.amazon.drs.drssampleandroidapp.Constants.DRS_API;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_IO_EXCEPTION;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_PARSER_EXCEPTION;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_RUNTIME_EXCEPTION;
import static com.amazon.drs.drssampleandroidapp.Constants.HEADER_ACCEPT_TYPE;
import static com.amazon.drs.drssampleandroidapp.Constants.HEADER_KEY_AUTHORIZATION;
import static com.amazon.drs.drssampleandroidapp.Constants.HEADER_VERSION_TYPE;
import static com.amazon.drs.drssampleandroidapp.Constants.HTTP_DELETE;
import static com.amazon.drs.drssampleandroidapp.Constants.HTTP_GET;
import static com.amazon.drs.drssampleandroidapp.Constants.HTTP_POST;
import static com.amazon.drs.drssampleandroidapp.Constants.REGISTRATION;
import static com.amazon.drs.drssampleandroidapp.Constants.REPLENISH;
import static com.amazon.drs.drssampleandroidapp.Constants.CANCEL_TEST_ORDER;
import static com.amazon.drs.drssampleandroidapp.Constants.CANCEL_ALL_TEST_ORDERS;
import static com.amazon.drs.drssampleandroidapp.Constants.SLOTS_DICTIONARY_NAME;
import static com.amazon.drs.drssampleandroidapp.Constants.SLOT_STATUS;
import static com.amazon.drs.drssampleandroidapp.Constants.SUBSCRIPTION_INFO;
import static com.amazon.drs.drssampleandroidapp.Constants.UTF8_ENCODING;

/**
 * This class is used for accessing Dash Replenishment Service.
 * All communication with the DRS server must be authenticated using the tokens from the LWA.
 * For more details on the DRS APIs please visit
 * https://developer.amazon.com/public/solutions/devices/dash-replenishment-service/docs/dash-replenish-endpoint
 *
 * @see IdentityHandler
 */
class CommunicationHandler {

    private static final String TAG = CommunicationHandler.class.getName();

    /**
     * Refers to the instance of activity from which the Communication handler is invoked.
     * This is used to delegate updates to the user.
     */
    private final MainActivity mMainActivity;

    /**
     * Constructs a new CommunicationHandler to make requests to the DRS service.
     * <p>
     * Some calls made using this new CommunicationHandler object are non blocking, and will update
     * the main activity when the call completes.
     * </p>
     *
     * @param mainActivity allows access to application-specific resources and views,
     *                     as well as calls for application-level operations such as updating the
     *                     customer devices access token, notification.
     * @see DrsUtils
     * @see IdentityHandler
     */
    CommunicationHandler(final MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    /**
     * Gets a string from the input stream.
     *
     * @param inputStream Stream received from the UrlConnection.
     * @return Response Message.
     * @throws IOException If any internal errors are encountered inside the client while
     *                     attempting to make the request to read the contents of the stream and parse it.
     *                     For example the HOST_FILE's input stream is no longer valid.
     */
    private static String getStringFromStream(final InputStream inputStream) throws IOException {
        return DrsUtils.getStringFromStream(inputStream);
    }

    /**
     * URL of the Amazon's host which currently hosts the DRS service.
     *
     * @return the url which points to the the DRS service.
     * @see DrsUtils .
     */
    private String getHost() {
        return DRS_API;
    }

    /**
     * Deregister the device.
     *
     * @param AccessToken Customer Access Token from LWA.
     */
    void deregisterDevice(final String AccessToken) {
        try {
            final String header = BEARER + AccessToken;
            createRequestTask(getHost() + REGISTRATION, HEADER_KEY_AUTHORIZATION, header,
                    ApiCallType.DEREGISTRATION);
        } catch (final RuntimeException runtimeException) {
            Log.e(TAG, ERROR_RUNTIME_EXCEPTION, runtimeException);
        }
    }

    /**
     * Replenishes the slot provided.
     *
     * @param slotId      The slot that needs to be replenished.
     * @param AccessToken Customer Access Token from LWA.
     */

    void initiateReplenish(final String slotId, final String AccessToken) {
        try {
            final String path = REPLENISH + URLEncoder.encode(slotId, UTF8_ENCODING);
            final String header = BEARER + AccessToken;
            createRequestTask(getHost() + path, HEADER_KEY_AUTHORIZATION,
                    header, ApiCallType.REPLENISH);
        } catch (final RuntimeException runtimeException) {
            Log.e(TAG, ERROR_RUNTIME_EXCEPTION, runtimeException);
        } catch (final IOException ioException) {
            Log.e(TAG, ERROR_IO_EXCEPTION, ioException);
        }
    }

    /**
     * Sends update about the current slots status.
     *
     * @param slotId      Slot ID.
     * @param AccessToken Customer Access Token from LWA.
     */
    void sendSlotStatus(final String slotId, final String AccessToken) {
        try {
            final String path = SLOT_STATUS + URLEncoder.encode(slotId, UTF8_ENCODING);
            final String header = BEARER + AccessToken;
            createRequestTask(getHost() + path, HEADER_KEY_AUTHORIZATION,
                    header, ApiCallType.SLOT_STATUS);
        } catch (final RuntimeException runtimeException) {
            Log.e(TAG, ERROR_RUNTIME_EXCEPTION, runtimeException);
        } catch (final IOException ioException) {
            Log.e(TAG, ERROR_IO_EXCEPTION, ioException);
        }
    }

    /**
     * Sends update about the device to report the device is still active.
     *
     * @param AccessToken Customer Access Token from LWA.
     */
    void deviceStatus(final String AccessToken) {
        try {
            final String header = BEARER + AccessToken;
            createRequestTask(getHost() + DEVICE_STATUS, HEADER_KEY_AUTHORIZATION,
                    header, ApiCallType.DEVICE_STATUS);
        } catch (final RuntimeException runtimeException) {
            Log.e(TAG, ERROR_RUNTIME_EXCEPTION, runtimeException);
        }
    }

    /**
     * Retrieve subscription information about the slots.
     *
     * @param AccessToken Customer Access Token from LWA.
     */
    void subscriptionInfo(final String AccessToken) {
        try {
            final String header = BEARER + AccessToken;
            createRequestTask(getHost() + SUBSCRIPTION_INFO, HEADER_KEY_AUTHORIZATION,
                    header, ApiCallType.SUBSCRIPTION_INFO);
        } catch (final RuntimeException runtimeException) {
            Log.e(TAG, ERROR_RUNTIME_EXCEPTION, runtimeException);
        }
    }

    /**
     * Cancel slot lock for slot id.
     *
     * @param slotId      The slot id wor which order needs to be canceled.
     * @param AccessToken Customer Access Token from LWA.
     */
    void cancelTestOrderForSlot(final String slotId, final String AccessToken) {
        try {
            final String path = CANCEL_TEST_ORDER + URLEncoder.encode(slotId, UTF8_ENCODING);
            final String header = BEARER + AccessToken;
            createRequestTask(getHost() + path, HEADER_KEY_AUTHORIZATION,
                    header, ApiCallType.CANCEL_TEST_ORDER);
        } catch (final RuntimeException runtimeException) {
            Log.e(TAG, ERROR_RUNTIME_EXCEPTION, runtimeException);
        } catch (final IOException ioException) {
            Log.e(TAG, ERROR_IO_EXCEPTION, ioException);
        }
    }

    /**
     * Cancel test orders for all slots.
     *
     * @param AccessToken Customer Access Token from LWA.
     */
    void cancelTestOrdersForAllSlots(final String AccessToken) {
        try {
            final String header = BEARER + AccessToken;
            createRequestTask(getHost() + CANCEL_ALL_TEST_ORDERS, HEADER_KEY_AUTHORIZATION,
                    header, ApiCallType.CANCEL_ALL_TEST_ORDERS);
        } catch (final RuntimeException runtimeException) {
            Log.e(TAG, ERROR_RUNTIME_EXCEPTION, runtimeException);
        }
    }

    /**
     * Creates a request task.
     *
     * @param hostName    The host to which the connection is made.
     * @param headerKey   Header key for a post/put message.
     * @param headerValue Header Value for a post/put message.
     * @param apiCallType Type of call.
     */
    private void createRequestTask(final String hostName, final String headerKey,
                                   final String headerValue, final ApiCallType apiCallType) {
        final RequestTaskParameters requestTaskParameters = new RequestTaskParameters(
                hostName, headerKey, headerValue, apiCallType);
        new RequestTask().execute(requestTaskParameters);
    }

    /**
     * Used to map various calls in the service.
     * Defines the versions of the DRS APIs inputs and results.
     */
    enum ApiCallType {
        DEREGISTRATION("com.amazon.dash.replenishment.DrsDeregisterInput@2.0",
                "com.amazon.dash.replenishment.DrsDeregisterResult@1.0"),


        REPLENISH("com.amazon.dash.replenishment.DrsReplenishInput@1.0",
                "com.amazon.dash.replenishment.DrsReplenishResult@1.0"),

        SLOT_STATUS("com.amazon.dash.replenishment.DrsSlotStatusInput@1.0",
                "com.amazon.dash.replenishment.DrsSlotStatusResult@1.0"),

        DEVICE_STATUS("com.amazon.dash.replenishment.DrsDeviceStatusInput@1.0",
                "com.amazon.dash.replenishment.DrsDeviceStatusResult@1.0"),

        SUBSCRIPTION_INFO("com.amazon.dash.replenishment.DrsSubscriptionInfoInput@1.0",
                "com.amazon.dash.replenishment.DrsSubscriptionInfoResult@2.0"),

        CANCEL_TEST_ORDER("com.amazon.dash.replenishment.DrsCancelTestOrdersInput@1.0",
                "com.amazon.dash.replenishment.DrsCancelTestOrdersResult@1.0"),

        CANCEL_ALL_TEST_ORDERS("com.amazon.dash.replenishment.DrsCancelTestOrdersInput@1.0",
                "com.amazon.dash.replenishment.DrsCancelTestOrdersResult@1.0");


        private String mAcceptTypeHeader;
        private String mVersionTypeHeader;

        /**
         * Creates a API Call Type object.
         *
         * @param versionTypeHeader Version used for DRS API.
         * @param acceptTypeHeader  Accept header used for DRS API.
         */
        ApiCallType(final String versionTypeHeader, final String acceptTypeHeader) {
            mVersionTypeHeader = versionTypeHeader;
            mAcceptTypeHeader = acceptTypeHeader;
        }

        /**
         * Gets the accept type header value for the API call.
         *
         * @return Accept type header value.
         */
        public String getAcceptTypeHeader() {
            return mAcceptTypeHeader;
        }

        /**
         * Gets the version of the DRS API to be used.
         *
         * @return Version type header value.
         */
        public String getVersionTypeHeader() {
            return mVersionTypeHeader;
        }
    }

    /**
     * Asynchronous task to perform request to hosts.
     */
    private class RequestTask extends AsyncTask<RequestTaskParameters, Void, Void> {
        /**
         * Creates a HttpURLConnection object.
         *
         * @param requestTaskParameters parameters passed for the request.
         * @return Connection object with headers set.
         * @throws IOException if not able to open url connection.
         * @see RequestTaskParameters
         */
        private HttpURLConnection getHttpUrlConnection(
                final RequestTaskParameters requestTaskParameters) throws IOException {
            final String hostName = requestTaskParameters.getHostName();
            final String headerKey = requestTaskParameters.getHeaderKey();
            final String headerValue = requestTaskParameters.getHeaderValue();
            final ApiCallType callType = requestTaskParameters.getApiCallType();
            final URL hostUrl = new URL(hostName);
            final HttpURLConnection urlConnection =
                    (HttpURLConnection) hostUrl.openConnection();

            urlConnection.setRequestProperty(headerKey, headerValue);
            urlConnection.setRequestProperty(HEADER_ACCEPT_TYPE, callType.getAcceptTypeHeader());
            urlConnection.setRequestProperty(HEADER_VERSION_TYPE, callType.getVersionTypeHeader());
            return urlConnection;
        }

        /**
         * Makes an asynchronous HttpUrlConnection to the DRS service.
         *
         * @param requestTask The request bundle.
         * @return null response when the request is complete.
         * @see RequestTaskParameters
         */
        @Override
        protected Void doInBackground(final RequestTaskParameters... requestTask) {
            try {
                final RequestTaskParameters requestTaskParameters = requestTask[0];
                final ApiCallType apiCallType = requestTaskParameters.getApiCallType();
                if (apiCallType == ApiCallType.DEREGISTRATION) {
                    deregister(requestTaskParameters);
                } else if (apiCallType == ApiCallType.SLOT_STATUS) {
                    slotStatus(requestTaskParameters);
                } else if (apiCallType == ApiCallType.REPLENISH) {
                    replenish(requestTaskParameters);
                } else if (apiCallType == ApiCallType.DEVICE_STATUS) {
                    deviceStatus(requestTaskParameters);
                } else if (apiCallType == ApiCallType.SUBSCRIPTION_INFO) {
                    subscriptionInfo(requestTaskParameters);
                } else if (apiCallType == ApiCallType.CANCEL_TEST_ORDER
                        || apiCallType == ApiCallType.CANCEL_ALL_TEST_ORDERS) {
                    cancelTestOrder(requestTaskParameters);
                }
            } catch (final Exception e) {
                Log.e(TAG, ERROR_PARSER_EXCEPTION, e);
                final String responseString = ERROR_PARSER_EXCEPTION + e;
                mMainActivity.updateNotificationView(responseString);
            }
            return null;
        }

        /**
         * Checks to see if there are any errors.
         *
         * @param urlConnection standard HttpURLConnection object
         * @return True on error. False on no errors.
         * @throws IOException if not able to open url connection.
         */
        private boolean isErrorStream(final HttpURLConnection urlConnection) throws IOException {
            final InputStream errorStream = urlConnection.getErrorStream();
            if (errorStream != null) {
                try {
                    final String responseString = getStringFromStream(errorStream);
                    mMainActivity.updateNotificationView(responseString);
                    Log.e(TAG, "Error Message: " + urlConnection.getResponseMessage() + "\n"
                            + responseString);
                    return true;
                } finally {
                    errorStream.close();
                }
            }
            return false;
        }

        /**
         * Performs deregistration.
         *
         * @param requestTaskParameters parameters passed for the request.
         * @throws IOException if not able to open url connection.
         * @see RequestTaskParameters
         */
        private void deregister(final RequestTaskParameters requestTaskParameters)
                throws IOException {
            final HttpURLConnection urlConnection =
                    getHttpUrlConnection(requestTaskParameters);
            urlConnection.setRequestMethod("DELETE");
            urlConnection.connect();
            if (isErrorStream(urlConnection)) {
                return;
            }
            final String responseString = urlConnection.getResponseMessage();
            mMainActivity.updateNotificationView(responseString);
        }

        /**
         * Performs slot status.
         *
         * @param requestTaskParameters parameters passed for the request.
         * @throws IOException if not able to open url connection.
         * @see RequestTaskParameters
         */
        private void slotStatus(final RequestTaskParameters requestTaskParameters)
                throws IOException {
            final HttpURLConnection urlConnection =
                    getHttpUrlConnection(requestTaskParameters);
            urlConnection.setRequestMethod(HTTP_POST);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE_JSON);

            //Sample data.
            final String body = "{\"lastUseDate\":\"2017-04-01\",\"remainingQuantityInUnit\":1," +
                    "\"originalQuantityInUnit\":2,\"expectedReplenishmentDate\":\"2017-04-7\"," +
                    "\"totalQuantityOnHand\":20}";
            final OutputStream outputStream = urlConnection.getOutputStream();
            try {
                final BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(outputStream, UTF8_ENCODING));
                writer.write(body);
                writer.flush();
                writer.close();
                outputStream.close();
                urlConnection.connect();
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            if (isErrorStream(urlConnection)) {
                return;
            }
            final InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                try {
                    final String responseString = getStringFromStream(inputStream);
                    mMainActivity.updateNotificationView(responseString);
                } finally {
                    inputStream.close();
                }
            }

        }

        /**
         * Performs device status.
         *
         * @param requestTaskParameters parameters passed for the request.
         * @throws IOException if not able to open url connection.
         */
        private void deviceStatus(final RequestTaskParameters requestTaskParameters)
                throws IOException {
            final HttpURLConnection urlConnection =
                    getHttpUrlConnection(requestTaskParameters);
            urlConnection.setRequestMethod(HTTP_POST);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE_JSON);
            final String body = "{\"mostRecentlyActiveDate\":\"2017-04-01\"}";
            final OutputStream outputStream = urlConnection.getOutputStream();
            try {
                final BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(outputStream, UTF8_ENCODING));
                writer.write(body);
                writer.flush();
                writer.close();
                outputStream.close();
                urlConnection.connect();
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            if (isErrorStream(urlConnection)) {
                return;
            }
            final InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                try {
                    final String responseString = getStringFromStream(inputStream);
                    mMainActivity.updateNotificationView(responseString);
                } finally {
                    inputStream.close();
                }
            }
        }

        /**
         * Performs purchase/replenish.
         *
         * @param requestTaskParameters parameters passed for the request.
         * @throws IOException if not able to open url connection.
         * @see RequestTaskParameters
         */
        private void replenish(final RequestTaskParameters requestTaskParameters)
                throws IOException {
            final HttpURLConnection urlConnection =
                    getHttpUrlConnection(requestTaskParameters);
            urlConnection.setRequestMethod(HTTP_POST);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            if (isErrorStream(urlConnection)) {
                return;
            }
            final InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                try {
                    final String responseString = getStringFromStream(inputStream);
                    mMainActivity.updateNotificationView(responseString);
                } finally {
                    inputStream.close();
                }
            }
        }

        /**
         * Performs subscription request.
         *
         * @param requestTaskParameters Parameters passed for the request.
         * @throws IOException if not able to open url connection.
         * @see RequestTaskParameters
         */
        private void subscriptionInfo(final RequestTaskParameters requestTaskParameters)
                throws IOException {
            final HttpURLConnection urlConnection =
                    getHttpUrlConnection(requestTaskParameters);
            urlConnection.setRequestMethod(HTTP_GET);
            urlConnection.connect();
            if (isErrorStream(urlConnection)) {
                return;
            }
            final InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                try {
                    final String responseString = getStringFromStream(inputStream);
                    mMainActivity.updateNotificationView(responseString);
                    final List<String> slotList = new ArrayList<String>();
                    try {
                        final JSONObject slots = new JSONObject(responseString).
                                getJSONObject(SLOTS_DICTIONARY_NAME);
                        final JSONArray slotIDs = slots.names();
                        for (int i = 0; i < slotIDs.length(); i++) {
                            try {
                                slotList.add(slotIDs.getString(i));
                            } catch (final JSONException e) {
                                Log.e(TAG, e.getLocalizedMessage());
                            }
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                    mMainActivity.updateSlotSelectionSpinner(slotList);
                } finally {
                    inputStream.close();
                }
            }
        }

        /**
         * Cancel test order request.
         *
         * @param requestTaskParameters parameters passed for the request.
         * @throws IOException if not able to open url connection.
         * @see RequestTaskParameters
         */
        private void cancelTestOrder(final RequestTaskParameters requestTaskParameters)
                throws IOException {
            final HttpURLConnection urlConnection =
                    getHttpUrlConnection(requestTaskParameters);
            urlConnection.setRequestMethod(HTTP_DELETE);
            urlConnection.connect();
            if (isErrorStream(urlConnection)) {
                return;
            }
            final InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                try {
                    final String responseString = getStringFromStream(inputStream);
                    mMainActivity.updateNotificationView(responseString);
                } finally {
                    inputStream.close();
                }
            }
        }
    }
}
