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

/**
 * Contains all the constants in the application.
 */
class Constants {

    /**
     * Private constructor. No need to have instances of this class.
     */
    private Constants() {
    }

    /**
     * Dash Replenishment Service UI endpoint.
     */
    static final String DRS_API = "https://dash-replenishment-service-na.amazon.com";

    /**
     * LWA URL endpoint.
     */
    static final String LWA_ENDPOINT = "https://api.amazon.com/auth/o2/token";

    /**
     * Dash Replenishment Service UI endpoint.
     */
    static final String DRS_WEB_ENDPOINT = "https://drs-web.amazon.com/";

    /**
     * Client ID of the LWA Security Profile. Can be found on developer.amazon.com under
     * Security Profiles details.
     */
    static final String CLIENT_ID
            = "amzn1.application-oa2-client.8b2ea609179a4bf9a73ca6171ac9b3fc";

    /**
     * Composite ID used to retrieve the teaser image. This will be provided to you by
     * Amazon once you go through certification process on the Self-Service portal.
     */
    static final String COMPOSITE_IMAGE_ID
            = "amzn1.dash.v1.composite.315d909f-e392-4040-8dba-48f8ec170a1c";

    /**
     * Messages.
     */
    static final String ERROR_LOGOUT = "Logout error.";
    static final String ERROR_RUNTIME_EXCEPTION = "Error occurred. ";
    static final String ERROR_IO_EXCEPTION = "Unable to open the file. ";
    static final String ERROR_PARSER_EXCEPTION = "Error on Parse. ";
    static final String ERROR_MESSAGE = "Error Message: ";
    static final String ERROR_MALFORMED_URL = "Error forming authentication host URL. ";
    static final String ERROR_CONNECTION = "Unable to connect to LWA host. ";
    static final String ERROR_DEVICE_MODEL = "Invalid Device Model.";
    static final String ERROR_DEVICE_SERIAL = "Invalid Device Serial.";
    static final String ERROR_COMPOSITE_ID = "Invalid Composite ID.";
    static final String ERROR_AUTHORIZATION = "Error when authorizing";
    static final String ERROR_WRONG_ACTIVITY = "Not initialized from the main activity";
    static final String INVALID_INPUT_TITLE = "INVALID INPUT";
    static final String INVALID_INPUT_MESSAGE = "invalid input ";
    static final String INVALID_CODE_CHALLENGE_METHOD = "Invalid code challenge method. ";
    static final String INVALID_ENCODING = "Url encoding error. ";
    static final String INVALID_JSON = "JSON Exception. ";
    static final String INVALID_LOGIN_REQUEST = "Problem with creating the login request.";
    static final String INVALID_LOGIN = "Login succeed, but did not receive data.";
    static final String SUCCESS_LOGOUT = "Logged out successful";
    static final String SUCCESS_LOGIN = "Login with amazon worked";
    static final String CANCEL_AUTHORIZATION
            = "Authorization was cancelled because the user pressed the cancel button.";

    /**
     * Collection of the scope that the AmazonAuthorizationManager uses to access
     * customer's information.
     */
    static final String AMZ_AUTH_SCOPE_DASH_REPLENISH = "dash:replenish";

    /**
     * The device model key that is used in the LWA flow.
     */
    static final String AMZ_AUTH_DEVICE_MODEL = "device_model";

    /**
     * The DSN model key that is used in the LWA flow.
     */
    static final String AMZ_AUTH_SERIAL = "serial";

    /**
     * The test key that is used in the LWA flow.
     */
    static final String AMZ_AUTH_TEST_DEVICE = "is_test_device";

    /**
     * Flag that indicates if this registration should allow marketplaces that have not yet been
     * certified.
     * For use in pre-release testing only, this flag must not be passed in by your released app in
     * production.
     */
    static final String AMZ_INCLUDE_NON_LIVE_DEVICES = "should_include_non_live";
    /**
     * Name of the JavaScript interface.
     */
    static final String DRS_ANDROID_NATIVE_OBJECT = "DrsAndroidNativeObject";

    /**
     * SHA_256 hash algorithm key used in the DRS APIs.
     * Tells the DRS that we are using SHA_256 hashing.
     */
    static final String SHA_256 = "S256";
    /**
     * SHA_256 hash algorithm.
     */
    static final String ALGORITHM_SHA_256 = "SHA-256";
    /**
     * Plain. Do not use hash algorithm.
     */
    static final String PLAIN = "plain";

    /**
     * UTF-8 encoding.
     */
    static final String UTF8_ENCODING = "UTF-8";

    /**
     * Header key part of the Registration, Deregistration, Replenish and Slot Status.
     * The value of the header in the key value pair may vary depending on the type of the API call.
     */
    static final String HEADER_KEY_AUTHORIZATION = "Authorization";

    /**
     * Header key for amazon accept type.
     */
    static final String HEADER_ACCEPT_TYPE = "x-amzn-accept-type";

    /**
     * Header key for amazon version type.
     */
    static final String HEADER_VERSION_TYPE = "x-amzn-type-version";

    /**
     * Request content type.
     */
    static final String CONTENT_TYPE_KEY = "Content-Type";

    /**
     * Request content type value.
     */
    static final String CONTENT_TYPE_VALUE_JSON = "application/json";

    /**
     * HTTP methods.
     */
    static final String HTTP_POST = "POST";
    static final String HTTP_GET = "GET";
    static final String HTTP_DELETE = "DELETE";

    /**
     * This URL fragment is used to invoke a call to DRS deregister API.
     */
    static final String REGISTRATION = "/registration";

    /**
     * Passed in the Authorization header with the access token from LWA.
     */
    static final String BEARER = "Bearer ";

    /**
     * This URL fragment is used to denote a call to DRS replenish
     * API with the device's AccessToken.
     */
    static final String REPLENISH = "/replenish/";

    /**
     * This URL fragment is used to denote a call to DRS slot status
     * API with the device's AccessToken.
     * A slot id is followed with this key which specifies which type of slot needs to be updated.
     */
    static final String SLOT_STATUS = "/slotStatus/";

    /**
     * This URL fragment is used to denote a call to DRS device status API with the AccessToken.
     */
    static final String DEVICE_STATUS = "/deviceStatus";

    /**
     * String constant used to obtain slot ids.
     */
    static final String SLOTS_DICTIONARY_NAME = "slotsSubscriptionStatus";

    /**
     * This URL fragment is used to denote a call to DRS device subscription info
     * API with the AccessToken.
     * A response indicates subscription status of the slots in the DRS device.
     */
    static final String SUBSCRIPTION_INFO = "/subscriptionInfo";

    /**
     * This URL fragment is used to denote a call to cancel test order for a specific slot.
     * A response indicates order status for a slot.
     */
    static final String CANCEL_TEST_ORDER = "/testOrders/slots/";

    /**
     * This URL fragment is used to denote a call to to cancel all test orders for this device.
     * A response indicates order status for all slots.
     */
    static final String CANCEL_ALL_TEST_ORDERS = "/testOrders";

    /**
     * Maximum length of input strings. Defined by DRS APIs.
     */
    static final int MAX_LENGTH = 64;

    /**
     * This is the pattern for a valid device serial / model.
     */
    static final String PATTERN = "[[\\w]|[+_-]]+";

    /**
     * This is the base directory in the host from which base indexing takes place.
     */
    static final String ACCIO_CUSTOMER_UI = "AccioCustomerUI/";

    /**
     * The android links cannot be directly passed as the file prefix, directly passing them leads
     * to the service looking in the internal directory. This prefix is used to translate from the
     * provided url to a local copy of the web page.
     */
    static final String STANDARD_URI_FILE_PREFIX = "file:///";

    /**
     * URL provided to Amazon in the settings page to redirect to the next step in your
     * application. This will be called when the user presses the 'exit' link.
     */
    static final String EXIT_URL_EMPTY = "/null";

    /**
     * URL provided to Amazon in the teaser page to redirect to the next step in your
     * application.  This will be called when the user presses the 'Remind me later' link.
     */
    static final String REMIND_ME_LATER_URL_PATH = "android_asset/RemindMeLater.html";

    /**
     * URL provided to Amazon in the success page to redirect back in to your
     * application.  This will be called when the user presses the 'done' link.
     */
    static final String REDIRECT_TO_APP_URL_PATH = "android_asset/RedirectToSampleApp.html";

    /**
     * URL provided to Amazon in the teaser page to redirect to the next step in your
     * application.  This will be called when the user presses the 'Skip' link.
     */
    static final String SKIP_URL_PATH = "android_asset/Skip.html";

    /**
     * URL provided to Amazon in the teaser page to redirect to the next step in your
     * application.  This will be called when the user presses the 'Getting Started' button.
     */
    static final String LOGIN_SCREEN_URL_PATH = "android_asset/LoginWeb.html";

    /**
     * LWA request fields.
     */
    static final String LWA_GRANT_TYPE = "grant_type";
    static final String LWA_AUTH_CODE = "authorization_code";
    static final String LWA_CODE = "code";
    static final String LWA_REDIRECT_URI = "redirect_uri";
    static final String LWA_CLIENT_ID = "client_id";
    static final String LWA_CODE_VERIFIER = "code_verifier";

    /**
     * Bundle keys used to send values across application.
     */
    static final String SERIAL_EXTRA = "com.amazon.drs.drssampleappandroid.serial";
    static final String TEST_EXTRA = "com.amazon.drs.drssampleappandroid.test";
    static final String DEVICE_MODEL_ID_EXTRA = "com.amazon.drs.drssampleappandroid.modelId";
    static final String COMPOSITE_ID_EXTRA = "com.amazon.drs.drssampleappandroid.compositeId";
    static final String INCLUDE_NON_LIVE_DEVICES_EXTRA =
            "com.amazon.drs.android.sampleapp.include_non_live_devices";
    static final String BUNDLE_EXTRA = "com.amazon.drs.drssampleappandroid.bundle";
    static final String CUSTOMER_ACCESS_TOKEN =
            "com.amazon.drs.drssampleappandroid.CustomerAccessToken";

}
