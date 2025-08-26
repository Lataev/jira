'''
Автоматическое завершение просроченных задач.
'''


from jira import JIRA, client, Issue
from secret import auth_data

import os
import logging
os.makedirs('logs', exist_ok=True)
logging.basicConfig(filename='logs/jira_auto_close_issues.log', level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s',)

def jira_auth():
    '''Аутентификация по логину-паролю, либо по токену'''
    return JIRA(
        basic_auth=(
            auth_data.jira_log_pass().login,
            auth_data.jira_log_pass().password,
        ),
        # token_auth=jira_log_pass().token,
        options={"server": auth_data.jira_log_pass().server, "verify": False},
    )

def search_jira_issues(jql_query) -> client.ResultList:
    '''Поиск запросов'''
    jira = jira_auth()
    issues = jira.search_issues(jql_query)
    jira.close()

    return issues

def close_issue(issue: Issue):
    '''Перевод запроса в статус Завершено'''
    jira = jira_auth()
    jira.add_comment('Запрос закрыт автоматически')
    logging.info(f'Запрос закрыт автоматически: {issue.key}')
    jira.transition_issue(issue, 1234) # 1234 - id перехода. Проставление резолюции вшито в пост-функцию перехода.
    jira.close()

if __name__ == '__main__':

    # jql фильтр
    jql_query = '''
                project = proj
                AND 
                "Дата завершения" < now()
                '''
    try:
        issues = search_jira_issues(jql_query)
        print(f"Найдено запросов: {issues.total}")

        for issue in issues:
            close_issue(issue)
            
    except Exception as e:
        logging.info(f'Ошибка:\t{e}')
