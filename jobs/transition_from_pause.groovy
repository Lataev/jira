'''
Изменение статуса задачи по достижении даты из поля "На паузе до".
'''

def query = 'project = project AND status = "На паузе" AND "На паузе до" <= now()'

def issues = Issues.search(query)

issues.each { issue ->
      issue.transition(123) // id перехода 'Из паузы'
}
