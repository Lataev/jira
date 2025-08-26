'''
Взаимодействие непосредственно с базой Jira.
'''

import com.atlassian.core.ofbiz.CoreFactory
import org.ofbiz.core.entity.*
import java.sql.Connection
import groovy.sql.Sql

GenericDelegator delegator = CoreFactory.getGenericDelegator()
String helperName = delegator.getGroupHelperName("default")
Connection conn = ConnectionFactory.getConnection(helperName)


// получение данных из бд
def sql = new Sql(conn)
def rows = sql.rows('''SELECT * FROM public."cwd_user" WHERE "lower_user_name" = 'admin' ''')
log.warn(rows[0].display_name)
log.warn(rows[0].email_address)
log.warn(rows[0].active)


// добавление данных в бд
def insert_in_db(Sql sql, String issue_key, String text) {
    sql.call("""INSERT INTO "LOG" ("issue_key", "text") VALUES ('$issue_key', '$text');""")
    sql.commit()
}

def issue_key = 'proj-001'
def text = 'text'
// insert_in_db(sql, issue_key, text)


conn.close()