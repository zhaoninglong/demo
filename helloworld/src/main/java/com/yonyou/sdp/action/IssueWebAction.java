package com.yonyou.sdp.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.jql.builder.JqlClauseBuilder;
import com.atlassian.jira.jql.builder.JqlQueryBuilder;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.sal.api.user.UserManager;

public class IssueWebAction extends JiraWebActionSupport {

	private static final Logger log = LoggerFactory
			.getLogger(IssueWebAction.class);
	private UserManager userManager;
	private com.atlassian.jira.user.util.UserManager jiraUserManager;
	private SearchService searchService;
	private List<Issue> issues;

	public IssueWebAction(UserManager userManager,
			com.atlassian.jira.user.util.UserManager jiraUserManager,
			SearchService searchService) {
		this.userManager = userManager;
		this.jiraUserManager = jiraUserManager;
		this.searchService = searchService;
	}

	protected String doExecute() throws Exception {
		User user = jiraUserManager.getUser(userManager.getRemoteUsername());
		JqlClauseBuilder jqlClauseBuilder = JqlQueryBuilder.newClauseBuilder();
		com.atlassian.query.Query query = jqlClauseBuilder.buildQuery();
		PagerFilter pagerFilter = PagerFilter.getUnlimitedFilter();
		com.atlassian.jira.issue.search.SearchResults searchResults = null;
		try {
			searchResults = searchService.search(user, query, pagerFilter);
		} catch (SearchException e) {
			e.printStackTrace();
		}
		issues = searchResults.getIssues();
		return INPUT;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
}
