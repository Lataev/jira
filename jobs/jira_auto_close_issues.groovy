'''
Автоматическое завершение просроченных задач.
'''


def jql = '''
            project = proj
            AND 
            "Дата завершения" < now()
            '''

def issues = Issues.search(jql)

Users.runAs('admin') {
    issues.each { issue ->
        issue.addComment('Запрос закрыт автоматически')
        log.warn("Запрос закрыт автоматически: $issue.key")
        issue.transition(1234) {  // 1234 - id перехода или название
            setResolution('Просрочено') // решение по запросу
        }
    }
}