'''
Получение значений полей запроса Jira
'''


from datetime import datetime
from jira import JIRA, Issue
from secret import auth_data


jira = JIRA(
    token_auth=auth_data.jira_log_pass().token,
    options={"server": auth_data.jira_log_pass().server, "verify": False},
)

def get_issue_data(issue: Issue):
    class Issue_fields:
        asset_field = issue.get_field('customfield_14000') # Поле актива, возвращает список 
        list_field = f"{issue.get_field('customfield_14001')}" # Поле выбора
        text_field = issue.get_field('customfield_14002').strip() # Текстовое поле
        issue_reporter = issue.fields.reporter # Автор запроса
        date_field = issue.get_field('customfield_14003') # Поле даты
        date = datetime.strptime(date_field, "%d.%m.%Y")
    return Issue_fields

if __name__ == '__main__':
    issue = jira.issue('PROJ-001') # Получение запроса jira
    issue_data = get_issue_data(issue=issue) # Получение значений полей запроса
    print(issue_data.asset_field)
    print(issue_data.list_field)
    print(issue_data.text_field)

    print('\n'.join(f'{key}\t\t{value}' for key, value in issue_data.__dict__.items())) # Посмотреть все ключи-значения класса

jira.close()