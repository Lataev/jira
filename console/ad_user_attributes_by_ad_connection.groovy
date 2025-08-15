'''
Получение значений атрибутов пользователя AD непосредственно из AD
'''

import com.onresolve.scriptrunner.ldap.LdapUtil
import org.springframework.ldap.core.AttributesMapper
import javax.naming.directory.SearchControls


if (issue.getCustomFieldValue('Пользователь')) {

    def jira_user_name = 'jira_user_name'
    def attributes = ['cn', 'title', 'department', 'company']
    def user = [:]

    LdapUtil.withTemplate("AD") { template ->

        template.search("", "(sAMAccountName=*$jira_user_name*)", SearchControls.SUBTREE_SCOPE, { attributes -> 

            for (attribute in attributes) {
                if (attributes.get(attribute)) {
                    user[attribute] = attributes.get(attribute).get().toString()
                }
            }
        
        } as AttributesMapper<Object>)
    }

    log.warn(user)

}