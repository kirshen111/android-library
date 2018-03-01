package com.example.elika.myapplication999;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Context mMockContext;

    @Mock
    Bundle bundle;

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        when(mMockContext.getString(R.id.about_description))
                .thenReturn(FAKE_STRING);
        AboutActivity myObjectUnderTest = new AboutActivity();

        myObjectUnderTest.onCreate(bundle);

    }
}