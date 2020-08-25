/*
 * Copyright (C) 2018 The Superior OS Project
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

package com.malluos.settings.fragments;

import android.content.Context;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.preference.SwitchPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceCategory;
import androidx.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.malluos.support.colorpicker.ColorPickerPreference;

import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;

import com.malluos.settings.R;

public class StatusbarSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

     private static final String MALLUOS_LOGO_COLOR = "status_bar_logo_color";
     private ColorPickerPreference mMalluOSLogoColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.malluos_settings_statusbar);
        ContentResolver resolver = getActivity().getContentResolver();
        final PreferenceScreen prefScreen = getPreferenceScreen();
        final ContentResolver resolver = getActivity().getContentResolver();


          mMalluOSLogoColor =
                  (ColorPickerPreference) findPreference(MalluOS_LOGO_COLOR);
          int intColor = Settings.System.getIntForUser(resolver,
                  Settings.System.STATUS_BAR_LOGO_COLOR, 0xFFFFFFFF,
                  UserHandle.USER_CURRENT);
          String hexColor = ColorPickerPreference.convertToARGB(intColor);
          mMalluOSLogoColor.setNewPreviewColor(intColor);
          if (intColor != 0xFFFFFFFF) {
              mMalluOSLogoColor.setSummary(hexColor);
          } else {
              mMalluOSLogoColor.setSummary(R.string.default_string);
          }
          mMalluOSLogoColor.setOnPreferenceChangeListener(this);

          public boolean onPreferenceChange(Preference preference, Object newValue) {

    ContentResolver resolver = getActivity().getContentResolver();
    if (preference == mMalluOSLogoColor) {
        String hex = ColorPickerPreference.convertToARGB(
            Integer.parseInt(String.valueOf(newValue)));
        int value = ColorPickerPreference.convertToColorInt(hex);
        Settings.System.putIntForUser(resolver,
            Settings.System.STATUS_BAR_LOGO_COLOR, value,
            UserHandle.USER_CURRENT);
        if (value != 0xFFFFFFFF) {
            mMalluOSLogoColor.setSummary(hex);
        } else {
            mMalluOSLogoColor.setSummary(R.string.default_string);
        }
        return true;
    }
  }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.MALLUOS_SETTINGS;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
