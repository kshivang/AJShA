package ligi.org.ajsha;

import android.test.ActivityInstrumentationTestCase2;

import com.google.android.apps.common.testing.ui.espresso.action.ClearTextAction;
import com.squareup.spoon.Spoon;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;

import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testThatInputIsThere() {
        Spoon.screenshot(getActivity(), "main");

        onView(withId(R.id.codeInput)).check(matches(isDisplayed()));
    }

    public void testThatOnePlusOneIsTwo() {

        final MainActivity activity = uiEvalCode("1+1");

        onView(withId(R.id.obj_tostring)).check(matches(withText("2")));

        Spoon.screenshot(activity, "one_plus_one");
    }

    public void testThatAndroidIsResolved() {

        final MainActivity activity = uiEvalCode("Build");

        onView(withId(R.id.obj_tostring)).check(matches(withText(endsWith("android.os.Build"))));
        onView(withId(R.id.obj_classinfo)).check(matches(withText(endsWith("android.os.Build"))));

        Spoon.screenshot(activity, "eval_build");
    }


    private MainActivity uiEvalCode(String code) {

        final MainActivity activity = getActivity();

        onView(withId(R.id.codeInput)).perform(new ClearTextAction());
        onView(withId(R.id.codeInput)).perform(typeText(code));

        onView(withId(R.id.execCodeButton)).perform(click());

        return activity;
    }

}
