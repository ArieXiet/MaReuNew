package com.ariexiet.maru;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.ariexiet.maru.di.DI;
import com.ariexiet.maru.service.MeetingApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

	private MeetingApiService mDummy;

	@Before
	public void setup() { mDummy = DI.getMeetingApiService();}


	@Rule
	public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void addMeetingTest() {
		RecyclerView mRecyclerView = mActivityTestRule.getActivity().findViewById(R.id.list_meeting);
		RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
		int itemCount = Objects.requireNonNull(adapter).getItemCount();
		ViewInteraction floatingActionButton = onView(
				allOf(withId(R.id.fab),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								1),
						isDisplayed()));
		floatingActionButton.perform(click());

		ViewInteraction textInputEditText = onView(
				allOf(withId(R.id.textInput_subject),
										withId(R.id.textInput_subject),
						isDisplayed()));
		textInputEditText.perform(replaceText("hdf"), closeSoftKeyboard());

		ViewInteraction appCompatTextView = onView(
				allOf(withId(R.id.date_big), withText("Date"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								7),
						isDisplayed()));
		appCompatTextView.perform(click());

		ViewInteraction appCompatButton = onView(
				allOf(withId(android.R.id.button1), withText("OK"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.widget.ScrollView")),
										0),
								3)));
		appCompatButton.perform(scrollTo(), click());

		ViewInteraction appCompatTextView2 = onView(
				allOf(withId(R.id.time_big), withText("Time"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								10),
						isDisplayed()));
		appCompatTextView2.perform(click());

		ViewInteraction appCompatButton2 = onView(
				allOf(withId(android.R.id.button1), withText("OK"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.widget.ScrollView")),
										0),
								3)));
		appCompatButton2.perform(scrollTo(), click());

		ViewInteraction appCompatTextView3 = onView(
				allOf(withId(R.id.checked_room), withText("checked room"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								13),
						isDisplayed()));
		appCompatTextView3.perform(click());

		ViewInteraction recyclerView = onView(
				allOf(withId(R.id.list_room_check),
						childAtPosition(
								withClassName(is("android.widget.LinearLayout")),
								0)));
		recyclerView.perform(actionOnItemAtPosition(4, click()));

		ViewInteraction appCompatCheckBox = onView(
				allOf(withId(R.id.checkbox),
						childAtPosition(
								allOf(withId(R.id.layout),
										childAtPosition(
												withId(R.id.list_room_check),
												4)),
								0),
						isDisplayed()));
		appCompatCheckBox.perform(click());

		ViewInteraction appCompatButton3 = onView(
				allOf(withId(R.id.room_button), withText("Valider"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								1),
						isDisplayed()));
		appCompatButton3.perform(click());

		ViewInteraction appCompatTextView4 = onView(
				allOf(withId(R.id.attendees), withText("selected attendees"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								16),
						isDisplayed()));
		appCompatTextView4.perform(click());

		ViewInteraction appCompatCheckBox2 = onView(
				allOf(withId(R.id.checkbox),
						childAtPosition(
								allOf(withId(R.id.layout),
										childAtPosition(
												withId(R.id.list_attendees_check),
												16)),
								0),
						isDisplayed()));
		appCompatCheckBox2.perform(click());

		ViewInteraction appCompatButton4 = onView(
				allOf(withId(R.id.attendees_button), withText("Valider"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								1),
						isDisplayed()));
		appCompatButton4.perform(click());

		ViewInteraction appCompatButton5 = onView(
				allOf(withId(R.id.button_create), withText("CREER"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								18),
						isDisplayed()));
		appCompatButton5.perform(click());

		int itemCountAfter = adapter.getItemCount();
		Assert.assertEquals((itemCount + 1), itemCountAfter);
	}

	@Test
	public void deleteMeetingTest() {
		RecyclerView mRecyclerView = mActivityTestRule.getActivity().findViewById(R.id.list_meeting);
		RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
		int itemCount = Objects.requireNonNull(adapter).getItemCount();
		ViewInteraction appCompatImageButton = onView(
				allOf(withId(R.id.item_list_delete_button), withContentDescription("delete button"),
						childAtPosition(
								childAtPosition(
										withId(R.id.list_meeting),
										7),
								3),
						isDisplayed()));
		appCompatImageButton.perform(click());
		int itemCountAfter = adapter.getItemCount();
		Assert.assertEquals((itemCount - 1), itemCountAfter);
			}

	@Test
	public void filterByDateTest() {
		Calendar dateToTest = Calendar.getInstance();
		dateToTest.set(2020, 7, 7);

		ViewInteraction overflowMenuButton = onView(
				allOf(withContentDescription("More options"),
						childAtPosition(
								childAtPosition(
										withId(R.id.action_bar),
										1),
								0),
						isDisplayed()));
		overflowMenuButton.perform(click());

		ViewInteraction appCompatTextView = onView(
				allOf(withId(R.id.title), withText("filtre par date"),
						childAtPosition(
								childAtPosition(
										withId(R.id.content),
										0),
								0),
						isDisplayed()));
		appCompatTextView.perform(click());

		onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).
				perform(PickerActions.setDate(2020, 8, 7));

		ViewInteraction appCompatButton = onView(
				allOf(withId(android.R.id.button1), withText("OK"),
						childAtPosition(
								childAtPosition(
										withClassName(is("android.widget.ScrollView")),
										0),
								3)));
		appCompatButton.perform(scrollTo(), click());

		RecyclerView mRecyclerView = mActivityTestRule.getActivity().findViewById(R.id.list_meeting);
		RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
		int itemCount = Objects.requireNonNull(adapter).getItemCount();

		Assert.assertEquals(itemCount, mDummy.getMeetingsByDate(dateToTest).size());
	}

	@Test
	public void filterByRoomTest() {
		ViewInteraction overflowMenuButton = onView(
				allOf(withContentDescription("More options"),
						childAtPosition(
								childAtPosition(
										withId(R.id.action_bar),
										1),
								0),
						isDisplayed()));
		overflowMenuButton.perform(click());

		ViewInteraction appCompatTextView = onView(
				allOf(withId(R.id.title), withText("filtre par salle"),
						childAtPosition(
								childAtPosition(
										withId(R.id.content),
										0),
								0),
						isDisplayed()));
		appCompatTextView.perform(click());

		ViewInteraction appCompatCheckBox = onView(
				allOf(withId(R.id.checkbox),
						childAtPosition(
								allOf(withId(R.id.layout),
										childAtPosition(
												withId(R.id.list_room_check),
												0)),
								0),
						isDisplayed()));
		appCompatCheckBox.perform(click());

		ViewInteraction appCompatButton = onView(
				allOf(withId(R.id.room_button), withText("Valider"),
						childAtPosition(
								childAtPosition(
										withId(R.id.container),
										0),
								1),
						isDisplayed()));
		appCompatButton.perform(click());

		RecyclerView mRecyclerView = mActivityTestRule.getActivity().findViewById(R.id.list_meeting);
		RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
		int itemCount = Objects.requireNonNull(adapter).getItemCount();

		Assert.assertEquals(itemCount, mDummy.getMeetingsByRoom(1).size());
	}

	private static Matcher<View> childAtPosition(
			final Matcher<View> parentMatcher, final int position) {

		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}

			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent)
						&& view.equals(((ViewGroup) parent).getChildAt(position));
			}
		};
	}
}
