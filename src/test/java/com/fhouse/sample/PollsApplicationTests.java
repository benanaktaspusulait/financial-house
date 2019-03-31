package com.fhouse.sample;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PollsApplicationTests {

	@Test
	public void contextLoads() {


	}

	@Test
	public void givenJavaFakersWithDifferentLocals_thenHeckZipCodesMatchRegex() {

		Faker ukFaker = new Faker(new Locale("en-GB"));
		Faker usFaker = new Faker(new Locale("en-US"));

		System.out.println(String.format("American zipcode: %s", usFaker.address().zipCode()));
		System.out.println(String.format("British postcode: %s", ukFaker.address().zipCode()));

		Pattern ukPattern = Pattern.compile(
				"([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|"
						+ "(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y]"
						+ "[0-9]?[A-Za-z]))))\\s?[0-9][A-Za-z]{2})");
		Matcher ukMatcher = ukPattern.matcher(ukFaker.address().zipCode());

		assertTrue(ukMatcher.find());

		Matcher usMatcher = Pattern.compile("^\\d{5}(?:[-\\s]\\d{4})?$").matcher(usFaker.address().zipCode());

		assertTrue(usMatcher.find());
	}
	@Test
	public void givenJavaFakersWithSameSeed_whenNameCalled_CheckSameName() {

		Faker faker1 = new Faker(new Random(24));
		Faker faker2 = new Faker(new Random(24));

		assertEquals(faker1.name().firstName(), faker2.name().firstName());
	}
}
