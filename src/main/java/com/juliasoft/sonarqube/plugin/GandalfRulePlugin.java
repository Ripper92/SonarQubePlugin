package com.juliasoft.sonarqube.plugin;

import com.google.common.collect.ImmutableList;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.*;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import java.util.List;

@Rule(key = "GandalfRulePlugin", name = "Return type and parameter of a method should not be the same", description = "For a method having a single parameter, the types of its return value and its parameter should never be the same.", priority = Priority.INFO, tags = {
		"style" })

public class GandalfRulePlugin extends IssuableSubscriptionVisitor {
	@Override
	public List<Kind> nodesToVisit() {
		final Kind[] CONTROL_STRUCTURES = { Kind.WHILE_STATEMENT, Kind.FOR_STATEMENT, Kind.DO_STATEMENT,
				Kind.FOR_EACH_STATEMENT };
		return ImmutableList.copyOf(CONTROL_STRUCTURES);
	}

	public void visitNode(Tree tree) {
		StatementTree myTree = (StatementTree) tree;
		boolean rootMethod = false;
		int i = 0;

		while (rootMethod == false) {
			if (myTree.is(Kind.WHILE_STATEMENT, Kind.FOR_EACH_STATEMENT, Kind.FOR_STATEMENT, Kind.DO_STATEMENT)) i++;
			if (myTree.parent().is(Kind.BLOCK)) {
				if (myTree.parent().parent().is(Kind.METHOD)) {
					rootMethod = true;
				} else {
					myTree = (StatementTree) myTree.parent().parent();
				}
			} else {
				myTree = (StatementTree) myTree.parent();
			}
		}
		if (i > 2) {
			reportIssue(tree, "There are more than 2 nested loops !");
		}
	}

}
