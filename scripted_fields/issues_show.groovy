'''
Multiple Issue Picker
Фотмат отображения запросов в поле. Статусы отображаются белым шрифтом с цветным фоном.
'''


import com.atlassian.jira.issue.Issue
import com.onresolve.scriptrunner.canned.util.OutputFormatter

renderIssueViewHtml = { Issue issue, String baseUrl ->
    OutputFormatter.markupBuilder {
        div {
            a(href: "${baseUrl}/browse/${issue.key}", issue.key)
            mkp.yield(': ')
            span(issue.getSummary())
            if (issue.status.name == 'Выполнено') {
                
                span(style: 'color: white; background-color: #00875A', '⠀Выполнено⠀')
            }
            else if (issue.status.name == 'Разработка') {
                
                span(style: 'color: white; background-color: #0052CC', ' В работе ')
            }
            else {
                span(style: 'color: white; background-color: #FFBA00', issue.status.name)
            }
        }
    }
}

renderIssueColumnHtml = renderIssueViewHtml
