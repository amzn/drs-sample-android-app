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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.SecureRandom;

import static com.amazon.drs.drssampleandroidapp.Constants.BUNDLE_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.COMPOSITE_ID_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.COMPOSITE_IMAGE_ID;
import static com.amazon.drs.drssampleandroidapp.Constants.DEVICE_MODEL_ID_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_COMPOSITE_ID;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_DEVICE_MODEL;
import static com.amazon.drs.drssampleandroidapp.Constants.ERROR_DEVICE_SERIAL;
import static com.amazon.drs.drssampleandroidapp.Constants.INCLUDE_NON_LIVE_DEVICES_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_INPUT_MESSAGE;
import static com.amazon.drs.drssampleandroidapp.Constants.INVALID_INPUT_TITLE;
import static com.amazon.drs.drssampleandroidapp.Constants.SERIAL_EXTRA;
import static com.amazon.drs.drssampleandroidapp.Constants.TEST_EXTRA;

/**
 * The final activity before calling in the teaser page.
 * Collect the serial, ModelId and Test Flag status here
 */
public class SetupProductStageFinalActivity extends Activity {

    private static final String TAG = SetupProductStageFinalActivity.class.getName();

    private final SecureRandom mRandom = new SecureRandom();
    private EditText mDSN;
    private EditText mDeviceModelId;
    private EditText mCompositeId;

    private CheckBox mIsTestEnabled;
    private CheckBox mIncludeNonLiveDevices;
    private TextView mTitleTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_product_stage_final);
        initializeUI();
    }

    private void raiseErrorAlert(final String errorMessage) {
        new AlertDialog.Builder(this)
                .setTitle(INVALID_INPUT_TITLE)
                .setMessage(errorMessage)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int which) {
                        Log.e(TAG, INVALID_INPUT_MESSAGE + errorMessage);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * Called when the user clicks the Send button.
     *
     * @param view pressed View
     */
    public void nextActivity(final View view) {
        final Intent intent = new Intent(this, MainHandlerActivity.class);
        final String serial = mDSN.getText().toString();
        final String modelId = mDeviceModelId.getText().toString();
        final String compositeId = mCompositeId.getText().toString();

        if (!DrsUtils.isValidInput(modelId)) {
            raiseErrorAlert(ERROR_DEVICE_MODEL);
            return;
        }
        if (!DrsUtils.isValidInput(serial)) {
            raiseErrorAlert(ERROR_DEVICE_SERIAL);
            return;
        }

        if (compositeId.isEmpty()) {
            raiseErrorAlert(ERROR_COMPOSITE_ID);
            return;
        }

        final Bundle bundle = new Bundle();
        bundle.putString(SERIAL_EXTRA, serial);
        if (validateCheckBox(mIsTestEnabled)) {
            bundle.putBoolean(TEST_EXTRA, mIsTestEnabled.isChecked());
        }
        bundle.putString(COMPOSITE_ID_EXTRA, compositeId);
        bundle.putString(DEVICE_MODEL_ID_EXTRA, modelId);
        if (validateCheckBox(mIncludeNonLiveDevices)) {
            bundle.putBoolean(INCLUDE_NON_LIVE_DEVICES_EXTRA, mIncludeNonLiveDevices.isChecked());
        }
        intent.putExtra(BUNDLE_EXTRA, bundle);

        startActivity(intent);
    }


    /**
     * Generate a random DSN.
     *
     * @param view pressed View
     */
    public void populateWithRandomDsn(final View view) {
        mDSN.setText(generateRandomDsn());
    }

    /**
     * Simulates the real serial number of the device.
     *
     * @return an alphanumeric string.
     */
    private String generateRandomDsn() {
        return new BigInteger(130, mRandom).toString(32);
    }

    /**
     * Validate the check box.
     *
     * @param checkBox CheckBox to validate.
     * @return true if the check box is valid, false otherwise.
     */
    private boolean validateCheckBox(final CheckBox checkBox) {
        return checkBox != null;
    }

    /**
     * Initialize the UI.
     */
    private void initializeUI() {
        mTitleTextView = (TextView) findViewById(R.id.title);
        mTitleTextView.setText(R.string.companion_app);
        mDSN = (EditText) findViewById(R.id.txtDSN);
        mDSN.setText(generateRandomDsn());
        mDeviceModelId = (EditText) findViewById(R.id.txtDeviceGroupId);
        mCompositeId = (EditText) findViewById(R.id.txtCompositeId);
        mCompositeId.setText(COMPOSITE_IMAGE_ID);
        mIsTestEnabled = (CheckBox) findViewById(R.id.checkbox_test);
        mIncludeNonLiveDevices = (CheckBox) findViewById(R.id.checkbox_include_non_live_devices);
    }
}
