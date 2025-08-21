'''
Простой поиск запросов в Jira
'''


from jira import JIRA, client
from secret import auth_data


def search_jira_issues(jql_query) -> client.ResultList:

    # Аутентификация по логину-паролю, либо по токену
    jira = JIRA(
        basic_auth=(
            auth_data.jira_log_pass().login,
            auth_data.jira_log_pass().password,
        ),
        # token_auth=jira_log_pass().token,
        options={"server": auth_data.jira_log_pass().server, "verify": False},
    )

    issues = jira.search_issues(jql_query)
    jira.close()

    return issues


if __name__ == '__main__':

    # jql фильтр
    jql_query = '''
                project = proj
                AND 
                "Тип запроса клиента" = Доступ
                AND 
                status in ( "В работе", Открыто )
                '''

    issues = search_jira_issues(jql_query)
    print(f"Найдено запросов: {issues.total}")
