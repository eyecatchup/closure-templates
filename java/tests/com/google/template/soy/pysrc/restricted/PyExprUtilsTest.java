/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.template.soy.pysrc.restricted;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


/**
 * Unit tests for CodeBuilder.
 *
 */
public final class PyExprUtilsTest extends TestCase {

  public void testConcatPyExprs() {
    List<PyExpr> exprs = new ArrayList<>();

    // Empty Array.
    assertEquals("''", PyExprUtils.concatPyExprs(exprs).getText());

    // Single String value.
    PyExpr foo = new PyStringExpr("foo");
    exprs.add(foo);
    assertEquals("foo", PyExprUtils.concatPyExprs(exprs).getText());

    // Single unknown value.
    exprs = new ArrayList<PyExpr>();
    foo = new PyExpr("foo", Integer.MAX_VALUE);
    exprs.add(foo);
    assertEquals("str(foo)", PyExprUtils.concatPyExprs(exprs).getText());

    // Multiple values are added to a list to be joined at a later time.
    PyExpr bar = new PyStringExpr("bar");
    PyExpr baz = new PyStringExpr("baz");
    exprs.add(bar);
    exprs.add(baz);
    assertEquals("[str(foo),bar,baz]", PyExprUtils.concatPyExprs(exprs).getText());
  }
}