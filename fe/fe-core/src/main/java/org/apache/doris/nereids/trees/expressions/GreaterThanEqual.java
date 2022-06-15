// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.trees.expressions;

import org.apache.doris.nereids.exceptions.UnboundException;
import org.apache.doris.nereids.trees.NodeType;
import org.apache.doris.nereids.trees.TreeNode;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * Greater than and equal expression: a >= b.
 */
public class GreaterThanEqual<LEFT_CHILD_TYPE extends Expression, RIGHT_CHILD_TYPE extends Expression>
        extends ComparisonPredicate<LEFT_CHILD_TYPE, RIGHT_CHILD_TYPE> {
    /**
     * Constructor of Greater Than And Equal.
     *
     * @param left  left child of Greater Than And Equal
     * @param right right child of Greater Than And Equal
     */
    public GreaterThanEqual(LEFT_CHILD_TYPE left, RIGHT_CHILD_TYPE right) {
        super(NodeType.GREATER_THAN_EQUAL, left, right);
    }

    @Override
    public boolean nullable() throws UnboundException {
        return left().nullable() || right().nullable();
    }

    @Override
    public String toString() {
        return "(" + left() + " >= " + right() + ")";
    }

    @Override
    public GreaterThanEqual newChildren(List<TreeNode> children) {
        Preconditions.checkArgument(children.size() == 2);
        return new GreaterThanEqual((Expression) children.get(0), (Expression) children.get(1));
    }
}
