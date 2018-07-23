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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.amazon.drs.drssampleandroidapp.Constants.BUNDLE_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.CUSTOMER_ACCESS_TOKEN;
import static com.amazon.drs.drssampleandroidapp.Constants.DEVICE_MODEL_ID_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.INCLUDE_NON_LIVE_DEVICES_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.SERIAL_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.TEST_EXTRA;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {


    final static String USER_DETAILS = "userDetails";
    private final static String TAG = MainActivity.class.getName();
    private final static String ACCESS_TOKEN = "AccessToken";
    private final static String REFRESH_TOKEN = "RefreshToken";
    private final static String TEXT_NOTIFICATION = "TextNotification";
    private final static String PRODUCT_SELECTION = "ProductSelection";
    private final static String TEST_BEHAVIOR = "TestBehaviour";
    private final static String CLEAR_VIEW = "";

    private SharedPreferences mPrefs;

    private Spinner mSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    private List<String> spinnerItems;

    // manages access tokens
    private IdentityHandler mIdentityHandler;

    // manages communication
    private CommunicationHandler mCommunicationHandler;

    private EditText mTextToken;
    private EditText mTextRefreshToken;
    private EditText mTextSlotId;
    private TextView mTextDeviceGroupId;
    private TextView mTextDSN;
    private TextView mTextNotification;
    private TextView mTextViewTitle;

    private String mTextNotificationValue;
    private String mClientID;
    private boolean mIsTestEnabled = false;
    private boolean mIncludeNonLiveDevices = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        // create an identity handler to get the access token
        mIdentityHandler = new IdentityHandler(this,
                CodeChallengeGenerator.getInstance(),
                new AuthorizationManagerWrapper());
        mCommunicationHandler = new CommunicationHandler(this);
        login();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIdentityHandler.resumeContext();
    }

    /**
     * Initialize the Application.
     */
    private void initialize() {
        mPrefs = getSharedPreferences(USER_DETAILS, MODE_PRIVATE);
        initializeUI();
        initializeSlotSpinner();
        getInitialParameters();
    }

    /**
     * This activity loads the Serial, ModelID and TestEnabled flag from the final stage.
     */
    private void getInitialParameters() {
        final Intent intent = getIntent();
        final Bundle bundle = intent.getBundleExtra(BUNDLE_EXTRA);
        if (bundle != null) {
            final String modelId = bundle.getString(DEVICE_MODEL_ID_EXTRA);
            final String serial = bundle.getString(SERIAL_EXTRA);
            mIsTestEnabled = bundle.getBoolean(TEST_EXTRA, false);
            mIncludeNonLiveDevices = bundle.getBoolean(INCLUDE_NON_LIVE_DEVICES_EXTRA, false);
            final CheckBox isTestCheck = ((CheckBox) findViewById(R.id.checkbox_test));
            final CheckBox includeNonLiveDevicesCheck =
                    ((CheckBox) findViewById(R.id.checkbox_include_non_live_devices));

            if (modelId != null) {
                mTextDeviceGroupId.setText(modelId);
            }
            if (isTestCheck != null) {
                isTestCheck.setChecked(mIsTestEnabled);
            }
            if (includeNonLiveDevicesCheck != null) {
                includeNonLiveDevicesCheck.setChecked(mIncludeNonLiveDevices);
            }
            if (serial != null) {
                mTextDSN.setText(serial);
            }
        }
    }

    /**
     * Initialize the UI.
     */
    private void initializeUI() {
        mTextViewTitle = (TextView) findViewById(R.id.title);
        mTextViewTitle.setText(R.string.drs_device);
        mTextToken = (EditText) findViewById(R.id.txtToken);
        mTextRefreshToken = (EditText) findViewById(R.id.txtRefreshToken);
        mTextDeviceGroupId = (TextView) findViewById(R.id.txtDeviceGroupId);
        mTextNotification = (TextView) findViewById(R.id.responseTextField);
        mTextDSN = (TextView) findViewById(R.id.txtDSN);
        mTextSlotId = (EditText) findViewById(R.id.txtSlotId);
        mSpinner = (Spinner) findViewById(R.id.slotSpinner);

        //get shared preferences
        mPrefs = getSharedPreferences(USER_DETAILS, MODE_PRIVATE);
        mIsTestEnabled = mPrefs.getBoolean(TEST_BEHAVIOR, true);
        mTextNotificationValue = mPrefs.getString(TEXT_NOTIFICATION, CLEAR_VIEW);

        // update values
        updateTokenTextView(mPrefs.getString(ACCESS_TOKEN, getString(R.string.access_token)));
        updateRefreshTokenTextView(mPrefs.getString(REFRESH_TOKEN, getString(R.string.refresh_token)));
        updateNotificationView(mTextNotificationValue);
        setButtonOnclickListeners();
    }

    /**
     * Initialize the spinner.
     */
    private void initializeSlotSpinner() {
        spinnerItems = new ArrayList<>();
        mSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                spinnerItems);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    /**
     * Sets the button's listeners.
     */
    private void setButtonOnclickListeners() {
        final Button mDeviceStatusButton = (Button) findViewById(R.id.sendDeviceStatus);
        mDeviceStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                deviceStatus();
            }
        });

        final Button mDeregisterDeviceButton = (Button) findViewById(R.id.DeRegisterDevice);
        mDeregisterDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                deregister();
            }
        });

        final Button mInitiateReplenishButton = (Button) findViewById(R.id.initiateReplenish);
        mInitiateReplenishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                replenish();
            }
        });

        final Button mSendSlotStatus = (Button) findViewById(R.id.sendSlotStatus);
        mSendSlotStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                slotStatus();
            }
        });

        final Button mNewAccessToken = (Button) findViewById(R.id.newAccessToken);
        mNewAccessToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getNewAccessToken();
            }
        });

        final Button mSubscriptionInfoButton = (Button) findViewById(R.id.subscriptionInfo);
        mSubscriptionInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                subscriptionInfo();
            }
        });

        final Button mGetSlotsButton = (Button) findViewById(R.id.getSlotsButton);
        mGetSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscriptionInfo();
            }
        });

        final Button cancelTestOrderButton = (Button) findViewById(R.id.cancelTestOrder);
        cancelTestOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                cancelTestOrder();
            }
        });

        final Button cancelAllTestOrdersButton = (Button) findViewById(R.id.cancelAllTestOrders);
        cancelAllTestOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                cancelTestOrdersForAllSlots();
            }
        });
    }

    /**
     * The user no longer wants to be associated with this device.
     * Deregister make the the device no longer functional.
     */
    private void deregister() {
        clearNotificationView();
        mCommunicationHandler.deregisterDevice(mTextToken.getText().toString());
    }

    /**
     * This performs purchase (replenishment) of the selected Asin.
     * For this its required to have a default Asin already setup for this device.
     */
    private void replenish() {
        clearNotificationView();
        mCommunicationHandler.initiateReplenish(mTextSlotId.getText().toString(),
                mTextToken.getText().toString());
    }

    /**
     * Is used to periodically send the slot status for a particular slot.
     */
    private void slotStatus() {
        clearNotificationView();
        mCommunicationHandler.sendSlotStatus(mTextSlotId.getText().toString(),
                mTextToken.getText().toString());
    }

    /**
     * Is used to periodically to report on whether the device is still active.
     */
    private void deviceStatus() {
        clearNotificationView();
        mCommunicationHandler.deviceStatus(mTextToken.getText().toString());
    }

    /**
     * Get the subscription status for the device's slots.
     */
    private void subscriptionInfo() {
        clearNotificationView();
        mCommunicationHandler.subscriptionInfo(mTextToken.getText().toString());
    }

    /**
     * Cancel test order for the selected slot.
     */
    private void cancelTestOrder() {
        clearNotificationView();
        mCommunicationHandler.cancelTestOrderForSlot(mTextSlotId.getText().toString(),
                mTextToken.getText().toString());
    }

    /**
     * Cancel test orders for all slots.
     */
    private void cancelTestOrdersForAllSlots() {
        clearNotificationView();
        mCommunicationHandler.cancelTestOrdersForAllSlots(mTextToken.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            mIdentityHandler = null;
            mPrefs = getSharedPreferences(USER_DETAILS, MODE_PRIVATE);
            final SharedPreferences.Editor editor = mPrefs.edit();
            editor.remove(USER_DETAILS);
            editor.clear();
            editor.apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        final SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(ACCESS_TOKEN, mTextToken.getText().toString());
        editor.putString(REFRESH_TOKEN, mTextRefreshToken.getText().toString());
        editor.putString(TEXT_NOTIFICATION, mTextNotificationValue);
        editor.putBoolean(TEST_BEHAVIOR, mIsTestEnabled);
        editor.putBoolean(PRODUCT_SELECTION, true);
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();
        if (mIdentityHandler != null) {
            if (id == R.id.Logout) {
                updateTokenTextView(CLEAR_VIEW);
                updateRefreshTokenTextView(CLEAR_VIEW);
                clearNotificationView();
                mIdentityHandler.logout();
            } else if (id == R.id.Login) {
                login();
            } else if (id == R.id.ChangeProductSelection) {
                changeProductSelection();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent(this, SetupProductStageFinalActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Calls LWA based login.
     */
    private void login() {
        if (mIdentityHandler != null) {
            mIdentityHandler.login();
        }
    }

    /**
     * Gets a new access token from the refresh token.
     */
    private void getNewAccessToken() {
        if (mIdentityHandler != null) {
            mIdentityHandler.getNewAccessToken(mTextRefreshToken.getText().toString());
        }
    }

    /**
     * Calls Product Selection.
     */
    private void changeProductSelection() {
        final Intent intent = new Intent(this, ChangeProductSelectionActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ACCESS_TOKEN, mTextToken.getText().toString());
        intent.putExtra(BUNDLE_EXTRA, bundle);
        startActivity(intent);
    }

    /**
     * Updates the access token text view.
     *
     * @param message Argument that replaces the current main message.
     */
    public void updateTokenTextView(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message != null && mTextToken != null) {
                    mTextToken.setText(message);
                }
            }
        });
    }

    /**
     * Updates the refresh token text view.
     *
     * @param message Argument that replaces the current main message.
     */
    public void updateRefreshTokenTextView(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message != null && mTextRefreshToken != null) {
                    mTextRefreshToken.setText(message);
                }
            }
        });
    }

    /**
     * Updates the notification view.
     *
     * @param message Argument that replaces the current main message.
     */
    public void updateNotificationView(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message != null && mTextNotification != null) {
                    mTextNotification.setText(message);
                    mTextNotificationValue = message;
                }
            }
        });
    }

    /**
     * Update the spinner with the new data.
     *
     * @param slotIDs List String of slot IDs.
     */
    public void updateSlotSelectionSpinner(final List<String> slotIDs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (slotIDs != null && spinnerItems != null) {
                    spinnerItems.clear();
                    spinnerItems.addAll(slotIDs);
                    mSpinnerAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * Clear the notification view.
     */
    private void clearNotificationView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTextNotification != null) {
                    mTextNotification.setText(CLEAR_VIEW);
                    mTextNotificationValue = CLEAR_VIEW;
                }
            }
        });
    }

    /**
     * Returns the customer entered device serial number.
     *
     * @return DSN of the device.
     */
    public String getDSN() {
        return mTextDSN.getText().toString();
    }

    /**
     * Returns the customer entered device model number.
     *
     * @return Model ID of the device.
     */
    public String getModelId() {
        return mTextDeviceGroupId.getText().toString();
    }


    /**
     * Test flag is enabled.
     *
     * @return true if enabled else false.
     */
    public boolean isTestEnabled() {
        return mIsTestEnabled;
    }

    /**
     * Include non live flag is enabled .
     *
     * @return true if enabled, false otherwise.
     */
    public boolean includeNonLiveDevices() {
        return mIncludeNonLiveDevices;
    }

    /**
     * Set up client ID.
     *
     * @param clientID String.
     */
    public void setClientID(final String clientID) {
        mClientID = clientID;
    }

    /**
     * Get the client ID.
     *
     * @return String client ID.
     */
    public String getClientID() {
        return mClientID;
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent,
                               final View view,
                               final int pos,
                               final long id) {
        final String selectedSlotID = (String) parent.getSelectedItem();
        mTextSlotId.setText(selectedSlotID);
    }

    @Override
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Log.i(TAG, "Nothing selected");
    }
}
