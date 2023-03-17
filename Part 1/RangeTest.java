/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2014, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * --------------
 * RangeTest.java
 * --------------
 * (C) Copyright 2003-2014, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sergei Ivanov;
 *
 * Changes
 * -------
 * 14-Aug-2003 : Version 1 (DG);
 * 18-Dec-2007 : Additional tests from Sergei Ivanov (DG);
 * 08-Jan-2012 : Added test for combine() method (DG);
 * 23-Feb-2014 : Added isNaNRange() test (DG);
 * 
 */

package org.jfree.data;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.jfree.chart.TestUtilities;
import org.junit.*;

/**
 * Tests for the {@link Range} class.
 */
public class RangeTest {
	private Range exampleRange;
	
    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-2, 3);
    }
    
    //--------------------------- getLowerBound()
    /**
     * This method is testing the getter method of lower bound
     */
    @Test
    public void getLowerBoundShouldBeMinusTwo() {
        assertEquals("The lower bound value of -2 and 3 should be -2",
        -2, exampleRange.getLowerBound(), .000000001d);
    }
    
    /**
     * This method is testing the getter method 
     * of lower bound with a new Range instance
     */
    @Test
    public void getLowerBoundShouldBeOne() {
    	Range R = new Range(1,5);
        assertEquals("The lower bound value of 1 and 5 should be 1",
        1, R.getLowerBound(), .000000001d);
    }
    
    //--------------------------- getUpperBound()
    /**
     * This method is testing the getter method
     * of the upper bound
     */
    @Test
    public void getUpperBoundShouldBeThree() {
        assertEquals("The upper bound value of -2 and 3 should be 3",
        3, exampleRange.getUpperBound(), .000000001d);
    }
    
    //--------------------------- getLength()
    /**
     * This method is testing the getLength() function of the
     * length between the lower and upper bounds
     */
    @Test
    public void getLengthShouldBeFive() {
        assertEquals("The central value of -2 and 3 should be 5",
        5, exampleRange.getLength(), .000000001d);
    }
    
    /**
     * This method is testing the getLength() function with
     * both the lower and upper bounds being the same
     */
    @Test
    public void getLengthShouldBeZero() {
    	Range R = new Range(0,0);
        assertEquals("The central value of 0 and 0 should be 0",
        0, R.getLength(), .000000001d);
    }
    
    //--------------------------- getCentralValue()
    /**
     * This method is testing the getCentralValue() which
     * gets the central value between the lower and upper
     * bounds of a range
     */
    @Test
    public void getCentralValueShouldBeZeroPointFive() {
        assertEquals("The central value of -2 and 3 should be 0.5",
        0.5, exampleRange.getCentralValue(), .000000001d);
    }
    
    //--------------------------- contains(double value)
    /**
     * This method is testing contains() for a value lower
     * than the lower bound
     */
    @Test
    public void containsShouldBeFalseLower() {
        assertFalse("The answer should be false",
        exampleRange.contains(-5));
    }
    
    /**
     * This method is testing contains() for a value bigger
     * than the upper bound
     */
    @Test
    public void containsShouldBeFalseUpper() {
        assertFalse("The answer should be false",
        exampleRange.contains(6.3));
    }
    
    /**
     * This method is testing contains() for a value close
     * to the lower bound, but bigger than it
     */
    @Test
    public void containsShouldBeTrueLowerBound() {
        assertTrue("The answer should be true",
        exampleRange.contains(-2));
    }
    
    /**
     * This method is testing contains() for a value close
     * to the upper bound, but smaller than it
     */
    @Test
    public void containsShouldBeTrueUpperBound() {
        assertTrue("The answer should be true",
        exampleRange.contains(3));
    }
    
    /**
     * This method is testing contains() for a value that is
     * bigger than the lower bound and smaller than the
     *  upper bound
     */
    @Test
    public void containsShouldBeTrueBetween() {
        assertTrue("The answer should be true",
        exampleRange.contains(0));
    }
    
    /**
     * This method is testing contains() for a value that is
     * bigger than the lower bound and smaller than the
     *  upper bound
     */
    @Test
    public void containsCorrect() {
        assertTrue("The answer should be true",
        exampleRange.contains(1));
    }
  
    //--------------------------- intersects(double b0, double b1)
    /**
     * This method is testing if the two points intersects
     * with the exampleRange range in the lower bound
     */
    @Test
    public void intersectsShouldBeTrueLower() {
        assertTrue("The lines interesect",
        exampleRange.intersects(-3, 0));
    }
    
    /**
     * This method is testing if the two points intersects
     * with the exampleRange range in between the range
     */
    @Test
    public void intersectsShouldBeTrueMiddle() {
        assertTrue("The lines interesect",
        exampleRange.intersects(1, 2));
    }
    
    /**
     * This method is testing if the two points intersects
     * with the exampleRange range in the upper bound
     */
    @Test
    public void intersectsShouldBeTrueUpper() { // This test case is not working
        assertTrue("The lines interesect",
        exampleRange.intersects(2, 6));
    }
    
    /**
     * This method is testing if the two points intersects
     * with the exampleRange range, where the two points are
     * overlaps the exampleRange
     */
    @Test
    public void intersectsShouldBeTrueLowerUpper() {
        assertTrue("The lines interesect",
        exampleRange.intersects(-4, 4));
    }
    
    /**
     * This method is testing if the two points intersects
     * with the exampleRange range in the lower bound where
     * the points are smaller than the lower bound
     */
    @Test
    public void intersectsShouldBeFalseLower() { // This test case is not working
        assertFalse("The lines do not interesect",
        exampleRange.intersects(-7, -4));
    }
    
    /**
     * This method is testing if the two points intersects
     * with the exampleRange range in the upper bound where
     * the points are bigger than the upper bound
     */
    @Test
    public void intersectsShouldBeFalseUpper() {
        assertFalse("The lines do not interesect",
        exampleRange.intersects(4, 6));
    }
    
    //--------------------------- intersects(Range range)
    /**
     * This method is testing if the given Range intersects
     * with the exampleRange range, range argument is
     * in between the exampleRange
     */
    @Test
    public void intersectsBoolean() {
    	Range R = new Range(0,1);
        assertTrue("The lines interesect",
        exampleRange.intersects(R));
    }

    //--------------------------- constrain()
    /**
     * This method is testing whether the function will return
     * the closest range bound to the input number, 
     * for this one the upper bound
     */
    @Test
    public void constrainBetween() {
    	assertEquals("The answer should be 2",
    	2 , exampleRange.constrain(2), .000000001d);
    }
    
    /**
     * This method is testing whether the function will return
     * the closest range bound to the input number, 
     * for this one the upper bound
     */
    @Test
    public void constrainUpperBound() {
    	assertEquals("The answer should be 3",
    	3 , exampleRange.constrain(4), .000000001d);
    }

    /**
     * This method is testing whether the function will return
     * the closest range bound to the input number, 
     * for this one the lower bound
     */
    @Test
    public void constrainLowerBound() {
    	assertEquals("The answer should be -2",
    	-2 , exampleRange.constrain(-3), .000000001d);
    }
    
    //--------------------------- combine(Range range1, Range range2)
    /**
     * This method is testing the combination of a null range with
     * a rational range which should return the rational range
     */
    @Test
    public void combineRange1Null() {
    	assertEquals("The range is -3 to 2",
    	exampleRange, Range.combine(null, exampleRange));
    }
    
    /**
     * This method is testing the combination of a rational range with
     * a null range which should return the rational range
     */
    @Test
    public void combineRange2Null() {
    	assertEquals("The range is -3 to 2",
    	exampleRange, Range.combine(exampleRange, null));
    }
    
    /**
     * This method is testing the combination of two ranges which should
     * be -3 to 3
     */
    @Test
    public void combineRight() {
    	Range R = new Range(1, 3);
    	Range C = new Range(-2, 3);
    	assertEquals("The range is -3 to 3",
    	C, Range.combine(exampleRange, R));
    }
    
    //--------------------------- combineIgnoringNaN(Range range1, Range range2)
    /**
     * This method is testing to combination of two ranges when they are 
     * both null, which should return null
     */
    @Test
    public void combineIgnoringNaNNullNull() {
        assertEquals("The returned value is null",
        null, Range.combineIgnoringNaN(null, null));
    }
    
    /**
     * This method is testing to combination of two ranges when they are 
     * null and NaN, which should return null
     */
    @Test
    public void combineIgnoringNaNNullNotNull() {
    	Range R = new Range(Double.NaN,Double.NaN);
        assertEquals("The returned value is a range of NaN",
        null, Range.combineIgnoringNaN(null, R));
    }
    
    /**
     * This method is testing to combination of two ranges when they are 
     * NaN and null, which should return null
     */
    @Test
    public void combineIgnoringNaNNotNullNull() {
    	Range R = new Range(Double.NaN,Double.NaN);
        assertEquals("The returned value is a range of NaN",
        null, Range.combineIgnoringNaN(R, null));
    }
    
    /**
     * This method is testing to combination of two ranges when they are 
     * valid range and null, which should return the valid range
     */
    @Test
    public void combineIgnoringNaNNumberNull() {
        assertEquals("The returned value is a range of -3 to 2",
        exampleRange, Range.combineIgnoringNaN(exampleRange, null));
    }
    
    /**
     * This method is testing to combination of two ranges when they are 
     * both valid ranges, which should return the largest possible range
     * between both of the ranges given
     */
    @Test
    public void combineIgnoringNaNNumberNumber() {
    	Range R = new Range(1,4);
    	Range C = new Range(-2,4);
        assertEquals("The returned value is a range of -2 to 4",
        C, Range.combineIgnoringNaN(exampleRange, R));
    }
    
    /**
     * This method is testing to combination of two ranges when they are 
     * both NaN, which should return null
     */
    @Test
    public void combineIgnoringNaNNaNNaN() {
    	Range R = new Range(Double.NaN,Double.NaN);
    	Range S = new Range(Double.NaN,Double.NaN);
        assertEquals("The returned value is a range of NaN",
        null, Range.combineIgnoringNaN(R, S));
    }
    
    //--------------------------- expandToInclude(Range range, double value)
    /**
     * This method is testing the expansion of a range to include a
     * given value, for this test, the range should return with the
     * lower bound extended to include the given value
     */
    @Test
    public void expandToIncludeValueLessThanLower() {
    	Range R = new Range(-4,3);
        assertEquals("The range should be -4 to 3",
        R, Range.expandToInclude(exampleRange, -4));
    }
    
    /**
     * This method is testing the expansion of a range to include a
     * given value, for this test, the range should return with the
     * upper bound extended to include the given value
     */
    @Test
    public void expandToIncludeValueMoreThanUpper() {
    	Range R = new Range(-2,4);
        assertEquals("The range should be -2 to 4",
        R, Range.expandToInclude(exampleRange, 4));
    }
    
    /**
     * This method is testing the expansion of a range to include a
     * given value, for this test, the range should return the
     * same range since the value is in between the range
     */
    @Test
    public void expandToIncludeEqual() {
        assertEquals("The range should be -3 to 2",
        exampleRange, Range.expandToInclude(exampleRange, 2));
    }
    
    /**
     * This method is testing the expansion of a range to include a
     * given value, for this test, the range should return with the
     * lower and upper bounds equal to the given value since the
     * given range is null
     */
    @Test
    public void expandToIncludeRangeNull() {
    	Range R = new Range(2,2);
        assertEquals("The range should be 2 to 2",
        R, Range.expandToInclude(null, 2));
    }
    
    //--------------------------- expand(Range range, double lowerMargin, double upperMargin)
    /**
     * This method is testing the expansion of the range with two margins,
     * lower and upper, which are supposed to multiple the lower and upper
     * bounds and add or subtract depending on the bound wanted to get the
     * new lower and upper bounds
     */
    @Test
    public void expandRange() {
    	Range R = new Range(18,23);
        assertEquals("The range should be 18 to 23",
        R, Range.expand(exampleRange, -4, 4));
    }
    
    //--------------------------- shift(Range base, double delta)
    /**
     * This method is testing the shifting of the range which in this test
     * it will shift 2 to the right of the given range
     */
    @Test
    public void shiftTwoArgs() {
    	Range R = new Range(0, 5);
    	assertEquals("The range should be 0 to 5",
    	R, Range.shift(exampleRange, 2));
    }
    
    //--------------------------- shift(Range base, double delta, boolean allowZeroCrossing)
    /**
     * This method is testing the shifting of the range which in this test
     * it will shift 2 to the right of the given range
     */
    @Test
    public void shiftTrue() {
    	Range R = new Range(0, 5);
    	assertEquals("The range should be 0 to 5",
    	R, Range.shift(exampleRange, 2, true));
    }
    
    /**
     * This method is testing the shifting of the range which in this test
     * it will shift 2 to the right of the given range
     */
    @Test
    public void shiftFalse() {
    	Range R = new Range(0, 5);
    	assertEquals("The range should be 0 to 5",
    	R, Range.shift(exampleRange, 2, false));
    }
    
    //--------------------------- scale(Range base, double factor)
    /**
     * This method is testing the scaling of the range, which given the
     * factor value, the range of the lower and upper bounds should multiply
     * by that value to get the new lower and upper bounds
     */
    @Test
    public void scalePositive() {
    	Range R = new Range(-4,6);
    	assertEquals("The range should be -4 to 6",
    	R, Range.scale(exampleRange, 2));
    }
    
    //--------------------------- equals(Object obj)
    /**
     * This method is testing if the lower bound of the given Object is
     * equal to the lower bound of the exampleRange
     */
    @Test
    public void equalsIsLower() {
    	Range R = new Range(1,2);
    	assertFalse("The lower bound of R is not equal to exampleRange lower bound",
    	exampleRange.equals(R));
    }
    
    /**
     * This method is testing if the Object argument is a Range object or
     * not
     */
    @Test
    public void equalsIsNotRange() {
    	assertFalse("The Object is not a Range",
    	exampleRange.equals("Fluminense"));
    }
    
    /**
     * This method is testing if the upper bound of the given Object is
     * equal to the lower bound of the exampleRange
     */
    @Test
    public void equalsIsUpper() {
    	Range R = new Range(-2,6);
    	assertFalse("The upper bound of R is not equal to exampleRange upper bound",
    	exampleRange.equals(R));
    }
    
    /**
     * This method is testing if the lower and upper bound of the Object
     * is equal to the lower and upper bound of exampleRange which should
     * be true
     */
    @Test
    public void equalsTrue() {
    	Range R = new Range(-2,3);
    	assertTrue("Both Ranges of exampleRange and R are equal",
    	exampleRange.equals(R));
    }
    
    //--------------------------- isNaNRange()
    /**
     * This method is testing if the lower and upper bounds are
     * a Double.NaN object
     */
    @Test
    public void isNaNRangeTrue() {
    	assertFalse("The range is NaN",
    	exampleRange.isNaNRange());
    }
    
    //--------------------------- hashCode()
    /**
     * This method is testing if the hash code generated is equal to the
     * calculated hash code for the lower and upper bounds
     */
    @Test
    public void hashCodeTest() {
        assertEquals("The hash code is 524288",
        524288 , exampleRange.hashCode());
    }
    
    //--------------------------- toString()
    /**
     * This method is testing the conversion of a negative lower bound
     * and positive upper bound to string
     */
    @Test
    public void toStringNegativePositive() {
        assertEquals("The answer should be 'Range[-2.0,3.0]'",
        "Range[-2.0,3.0]" , exampleRange.toString());
    }
    
    /**
     * This method is testing the conversion of a positive lower bound
     * and positive upper bound to string
     */
    @Test
    public void toStringPositivePositive() {
    	Range R = new Range(2, 4.2);
        assertEquals("The answer should be 'Range[2.0,4.2]'",
        "Range[2.0,4.2]" , R.toString());
    }
    
    /**
     * This method is testing the conversion of a negative lower bound
     * and negative upper bound to string
     */
    @Test
    public void toStringNegativeNegative() {
    	Range R = new Range(-4.3, -1.2);
        assertEquals("The answer should be 'Range[-4.3,-1.2]'",
        "Range[-4.3,-1.2]" , R.toString());
    }
    
    // TESTS FOR LAB 4
    
    /**
	 * This method is testing lower bound function with mutations
	 */
	@Test
	public void minLowerBound() {
		double A = Math.sqrt(-11);
		double B = Math.sqrt(-1);
		Range R = new Range(1, 4);
		Range R2 = Range.combineIgnoringNaN(R, new Range(A, B)); 
		assertEquals("The value should be 1", R2.getLowerBound(), 1, .000000001d);
	}
	
	/**
	 * This method is testing scaling for lower bound of range is correct after scaling
	 */
	@Test
	public void scaleLowerBound() {
		Range R = new Range(2, 6);
		Range test = Range.scale(R, 0);
		assertEquals("The shifted value should be 0", 0, test.getLowerBound(), .000000001d);
	}
	
    /**
     * This method is testing the getCentralValue() which
     * gets the central value between the lower and upper
     * bounds of a range
     */
    @Test
    public void getCentralValueShouldBeZeroPointFive2() {
    	double R = exampleRange.getCentralValue();
    	R = exampleRange.getCentralValue();
        assertEquals("The central value of -2 and 3 should be 0.5", 0.5, R, .000000001d);
    }
    
    /**
     * This method is testing the intersects function
	 */
	@Test
	public void intersectsRangeIsFalse() {
		Range R = new Range(1, 3);
		double lower = 5;
		double upper = 11;
		Range R2 = new Range(lower, upper);
		boolean test = R.intersects(R2);
		assertFalse("The expected output should be false", test);
	}
	
	/**
	 * This method is testing the lower boundary of intersect
	 */
	@Test
	public void intersectsBoundary() {
		Range R = new Range(1, 4);
		boolean test = R.intersects(1, 1);
		assertFalse("The result should be false", test);
	}

	/**
	 * This method is testing intersection with parameters inside range
	 */
	@Test
	public void intersectsRangeInsideRange() {
		Range R = new Range(1, 3); 
		double lower = 2;
		double upper = 2;
		boolean test = R.intersects(lower, upper);
		assertTrue("The output should be true", test);
	}
	
	/**
	 * This method is testing intersect for bigger upper bound than b1 and
	 * smaller lower bound than b0
	 */
	@Test
	public void intersectsSecondArgBigger() {
		Range R = new Range(1, 3);
		double lower = 0;
		double upper = 5;
		Range R2 = new Range(lower, upper);
		boolean test = R.intersects(R2);
		assertTrue("The result should be true", test);
	}

	/**
	 * This method is testing intersection with parameters inside range
	 */
	@Test
	public void intersectsRangeInsideRange2() {
		Range R = new Range(1, 4);
		double lower = 3;
		double upper = 2;
		boolean test = R.intersects(lower, upper);
		assertFalse("The expected output should be false", test);
	}
	
	/**
	 * This method is testing if shift function is making the upper bound
	 * of range match the correct value after shifted
	 */
	@Test
	public void shiftWithNoZeroCrossingWhereValueEqualsZero() {
		Range R = new Range(0, 0);
		Range test = Range.shift(R, 11, false);
		assertEquals("The shifted value should be 11", 11, test.getUpperBound(), .000000001d);
	}

	/**
	 * This method is testing equals function to check if two Range objects are equal
	 */
	@Test
	public void equalsUpperDiff() {
		Range R = new Range(1, 4);
		boolean test = R.equals(new Range(1, 5));
		assertFalse("Ranges are equal", test);
	}
    
    /**
     * This method is testing if the hash code generated is equal to the
     * calculated hash code for the lower and upper bounds
     */
    @Test
    public void hashCodeTest2() {
    	int hash = exampleRange.hashCode();
    	hash = exampleRange.hashCode();
        assertEquals("The hash code is 524288", 524288 , hash);
    }
    
    /**
     * This method is testing if the hash code generated is equal to the
     * calculated hash code for the lower and upper bounds
     */
    @Test
    public void hashCodeTest3() {
    	Range R = new Range(-1, 1);
    	R = new Range(-1, 1);
    	int I = R.hashCode();
        assertEquals("The hash code is 524288", -31457280 , I);
    }
    
    /**
     * This method is testing the conversion of a negative lower bound
     * and negative upper bound to string
     */
    @Test
    public void toStringNegativeNegative2() {
    	Range R = new Range(-4.3, -1.2);
    	String S = R.toString();
    	S = R.toString();
        assertEquals("The answer should be 'Range[-4.3,-1.2]'", "Range[-4.3,-1.2]" , S);
    }
	
	/**
     * Confirm that the constructor initializes all the required fields.
     */
    @Test
    public void testConstructor() {
        Range r1 = new Range(0.1, 1000.0);
        assertEquals(r1.getLowerBound(), 0.1, 0.0d);
        assertEquals(r1.getUpperBound(), 1000.0, 0.0d);

        try {
            /*Range r2 =*/ new Range(10.0, 0.0);
            fail("Lower bound cannot be greater than the upper");
        }
        catch (Exception e) {
            // expected
        }
    }

    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        Range r1 = new Range(0.0, 1.0);
        Range r2 = new Range(0.0, 1.0);
        assertEquals(r1, r2);
        assertEquals(r2, r1);

        r1 = new Range(0.0, 1.0);
        r2 = new Range(0.5, 1.0);
        assertFalse(r1.equals(r2));

        r1 = new Range(0.0, 1.0);
        r2 = new Range(0.0, 2.0);
        assertFalse(r1.equals(r2));

        // a Range object cannot be equal to a different object type
        assertFalse(r1.equals(new Double(0.0)));
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        Range a1 = new Range(1.0, 100.0);
        Range a2 = new Range(1.0, 100.0);
        assertEquals(a1.hashCode(), a2.hashCode());

        a1 = new Range(-100.0, 2.0);
        a2 = new Range(-100.0, 2.0);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    /**
     * Simple tests for the contains() method.
     */
    @Test
    public void testContains() {
        Range r1 = new Range(0.0, 1.0);
        assertFalse(r1.contains(Double.NaN));
        assertFalse(r1.contains(Double.NEGATIVE_INFINITY));
        assertFalse(r1.contains(-1.0));
        assertTrue(r1.contains(0.0));
        assertTrue(r1.contains(0.5));
        assertTrue(r1.contains(1.0));
        assertFalse(r1.contains(2.0));
        assertFalse(r1.contains(Double.POSITIVE_INFINITY));
    }

    /**
     * Tests the constrain() method for various values.
     */
    @Test
    public void testConstrain() {
        Range r1 = new Range(0.0, 1.0);

        double d = r1.constrain(0.5);
        assertEquals(0.5, d, 0.0000001);

        d = r1.constrain(0.0);
        assertEquals(0.0, d, 0.0000001);

        d = r1.constrain(1.0);
        assertEquals(1.0, d, 0.0000001);

        d = r1.constrain(-1.0);
        assertEquals(0.0, d, 0.0000001);

        d = r1.constrain(2.0);
        assertEquals(1.0, d, 0.0000001);

        d = r1.constrain(Double.POSITIVE_INFINITY);
        assertEquals(1.0, d, 0.0000001);

        d = r1.constrain(Double.NEGATIVE_INFINITY);
        assertEquals(0.0, d, 0.0000001);

        d = r1.constrain(Double.NaN);
        assertTrue(Double.isNaN(d));
    }

    /**
     * Simple tests for the intersects() method.
     */
    @Test
    public void testIntersects() {
        Range r1 = new Range(0.0, 1.0);
        assertFalse(r1.intersects(-2.0, -1.0));
        assertFalse(r1.intersects(-2.0, 0.0));
        assertTrue(r1.intersects(-2.0, 0.5));
        assertTrue(r1.intersects(-2.0, 1.0));
        assertTrue(r1.intersects(-2.0, 1.5));
        assertTrue(r1.intersects(0.0, 0.5));
        assertTrue(r1.intersects(0.0, 1.0));
        assertTrue(r1.intersects(0.0, 1.5));
        assertTrue(r1.intersects(0.5, 0.6));
        assertTrue(r1.intersects(0.5, 1.0));
        assertTrue(r1.intersects(0.5, 1.5));
        assertFalse(r1.intersects(1.0, 1.1));
        assertFalse(r1.intersects(1.5, 2.0));
    }

    /**
     * A simple test for the expand() method.
     */
    @Test
    public void testExpand() {
        Range r1 = new Range(0.0, 100.0);
        Range r2 = Range.expand(r1, 0.10, 0.10);
        assertEquals(-10.0, r2.getLowerBound(), 0.001);
        assertEquals(110.0, r2.getUpperBound(), 0.001);

        // Expand by 0% does not change the range
        r2 = Range.expand(r1, 0.0, 0.0);
        assertEquals(r1, r2);

        try {
            Range.expand(null, 0.1, 0.1);
            fail("Null value is accepted");
        }
        catch (Exception e) {
        }

        // Lower > upper: mid point is used
        r2 = Range.expand(r1, -0.8, -0.5);
        assertEquals(65.0, r2.getLowerBound(), 0.001);
        assertEquals(65.0, r2.getUpperBound(), 0.001);
    }

    /**
     * A simple test for the scale() method.
     */
    @Test
    public void testShift() {
        Range r1 = new Range(10.0, 20.0);
        Range r2 = Range.shift(r1, 20.0);
        assertEquals(30.0, r2.getLowerBound(), 0.001);
        assertEquals(40.0, r2.getUpperBound(), 0.001);

        r1 = new Range(0.0, 100.0);
        r2 = Range.shift(r1, -50.0, true);
        assertEquals(-50.0, r2.getLowerBound(), 0.001);
        assertEquals(50.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, 20.0, true);
        assertEquals(10.0, r2.getLowerBound(), 0.001);
        assertEquals(40.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, -30.0, true);
        assertEquals(-40.0, r2.getLowerBound(), 0.001);
        assertEquals(-10.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, 20.0, false);
        assertEquals(0.0, r2.getLowerBound(), 0.001);
        assertEquals(40.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, -30.0, false);
        assertEquals(-40.0, r2.getLowerBound(), 0.001);
        assertEquals(0.0, r2.getUpperBound(), 0.001);

        // Shifting with a delta of 0 does not change the range
        r2 = Range.shift(r1, 0.0);
        assertEquals(r1, r2);

        try {
            Range.shift(null, 0.1);
            fail("Null value is accepted");
        }
        catch (Exception e) {
        }
    }

    /**
     * A simple test for the scale() method.
     */
    @Test
    public void testScale() {
        Range r1 = new Range(0.0, 100.0);
        Range r2 = Range.scale(r1, 0.10);
        assertEquals(0.0, r2.getLowerBound(), 0.001);
        assertEquals(10.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 100.0);
        r2 = Range.scale(r1, 2.0);
        assertEquals(-20.0, r2.getLowerBound(), 0.001);
        assertEquals(200.0, r2.getUpperBound(), 0.001);

        // Scaling with a factor of 1 does not change the range
        r2 = Range.scale(r1, 1.0);
        assertEquals(r1, r2);

        try {
            Range.scale(null, 0.1);
            fail("Null value is accepted");
        }
        catch (Exception e) {
        }

        try {
            Range.scale(r1, -0.5);
            fail("Negative factor accepted");
        }
        catch (Exception e) {
        }
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        Range r1 = new Range(25.0, 133.42);
        Range r2 = (Range) TestUtilities.serialised(r1);
        assertEquals(r1, r2);
    }

    private static final double EPSILON = 0.0000000001;

    /**
     * Some checks for the combine method.
     */
    @Test
    public void testCombine() {
        Range r1 = new Range(1.0, 2.0);
        Range r2 = new Range(1.5, 2.5);

        assertNull(Range.combine(null, null));
        assertEquals(r1, Range.combine(r1, null));
        assertEquals(r2, Range.combine(null, r2));
        assertEquals(new Range(1.0, 2.5), Range.combine(r1, r2));

        Range r3 = new Range(Double.NaN, 1.3);
        Range rr = Range.combine(r1, r3);
        assertTrue(Double.isNaN(rr.getLowerBound()));
        assertEquals(2.0, rr.getUpperBound(), EPSILON);

        Range r4 = new Range(1.7, Double.NaN);
        rr = Range.combine(r4, r1);
        assertEquals(1.0, rr.getLowerBound(), EPSILON);
        assertTrue(Double.isNaN(rr.getUpperBound()));
    }

    /**
     * Some checks for the combineIgnoringNaN() method.
     */
    @Test
    public void testCombineIgnoringNaN() {
        Range r1 = new Range(1.0, 2.0);
        Range r2 = new Range(1.5, 2.5);

        assertNull(Range.combineIgnoringNaN(null, null));
        assertEquals(r1, Range.combineIgnoringNaN(r1, null));
        assertEquals(r2, Range.combineIgnoringNaN(null, r2));
        assertEquals(new Range(1.0, 2.5), Range.combineIgnoringNaN(r1, r2));

        Range r3 = new Range(Double.NaN, 1.3);
        Range rr = Range.combineIgnoringNaN(r1, r3);
        assertEquals(1.0, rr.getLowerBound(), EPSILON);
        assertEquals(2.0, rr.getUpperBound(), EPSILON);

        Range r4 = new Range(1.7, Double.NaN);
        rr = Range.combineIgnoringNaN(r4, r1);
        assertEquals(1.0, rr.getLowerBound(), EPSILON);
        assertEquals(2.0, rr.getUpperBound(), EPSILON);
    }
    
    @Test
    public void testIsNaNRange() {
        assertTrue(new Range(Double.NaN, Double.NaN).isNaNRange());
        assertFalse(new Range(1.0, 2.0).isNaNRange());
        assertFalse(new Range(Double.NaN, 2.0).isNaNRange());
        assertFalse(new Range(1.0, Double.NaN).isNaNRange());
    }
    
    @After
    public void tearDown() throws Exception {
    }
}
