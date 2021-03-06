/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.sql.impl.calcite.opt;

import com.google.common.collect.ImmutableList;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.plan.volcano.VolcanoPlanner;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.tools.Program;
import org.apache.calcite.tools.Programs;
import org.apache.calcite.tools.RuleSet;

/**
 * Performs query planning.
 */
public class QueryPlanner {

    private final VolcanoPlanner planner;

    public QueryPlanner(VolcanoPlanner planner) {
        this.planner = planner;
    }

    public RelNode optimize(RelNode node, RuleSet rules, RelTraitSet traitSet) {
        Program program = Programs.of(rules);

        return program.run(
            planner,
            node,
            traitSet,
            ImmutableList.of(),
            ImmutableList.of()
        );
    }
}
