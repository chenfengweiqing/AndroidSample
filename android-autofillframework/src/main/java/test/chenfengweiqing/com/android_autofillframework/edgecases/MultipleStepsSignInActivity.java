/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.chenfengweiqing.com.android_autofillframework.edgecases;

import android.view.View;

import com.google.common.collect.ImmutableMap;

import java.util.LinkedHashMap;
import java.util.Map;

import test.chenfengweiqing.com.android_autofillframework.R;

public class MultipleStepsSignInActivity extends AbstractMultipleStepsActivity {

    @Override
    protected Map<Integer, String> getStepsMap() {
        LinkedHashMap<Integer, String> steps = new LinkedHashMap<>(2);
        steps.put(R.string.username_label, View.AUTOFILL_HINT_USERNAME);
        steps.put(R.string.password_label, View.AUTOFILL_HINT_PASSWORD);
        return ImmutableMap.copyOf(steps);
    }
}
