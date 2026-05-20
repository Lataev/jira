'''
Поиск ассетов по iql и получение списка логинов из атрибута типа Пользователь Jira
'''


from atlassian import Insight
from secret import auth_data

import os
import logging
os.makedirs('logs', exist_ok=True)
logging.basicConfig(filename='logs/assets_search.log', level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s',)


insight = Insight(
        url=auth_data.insight().server,
        token=auth_data.insight().token,
        verify_ssl=False
        )


def get_name(asset: dict, user_attr_id: int):
        
    for attr in asset['attributes']:
        if attr['objectTypeAttributeId'] == user_attr_id:
            return attr['objectAttributeValues'][0]['user']['name']


def get_attribute_value(asset: dict, attr_id: int):
        
    for attr in asset['attributes']:
        if attr['objectTypeAttributeId'] == attr_id:
            return attr['objectAttributeValues'][0]['value']


def main(iql_query: str, scheme_id: int, user_attr_id: int = None, other_attr_id: int = None):
    
    assets = insight.iql(iql_query, scheme_id)

    logging.info(f'Количество найденных ассетов: {len(assets['objectEntries'])}')


    if len(assets['objectEntries']) and user_attr_id: # Получение Логинов найденных объектов по id атрибута Jira User

        asset_name_list = [get_name(asset) for asset in assets['objectEntries'] if get_name(asset, user_attr_id)]
        logging.info('Количество найденных логинов:', len(asset_name_list))
        logging.info(f'Логины: {', '.join(asset_name_list)}')


    if len(assets['objectEntries']) and other_attr_id: # Получение списка значений атрибута

        atr_value_list = [get_attribute_value(asset) for asset in assets['objectEntries'] if get_attribute_value(asset, other_attr_id)]
        logging.info('Количество найденных ассетов:', len(atr_value_list))
        logging.info(f'Стписок: {', '.join(atr_value_list)}')
        

if __name__ == '__main__':

    iql_query = 'objectTypeId = 249 and Status = 5' # поиск по id типа объекта и атрибуту Статус (5 - Требуется действие, 1 - Активно)
    scheme_id = 4 # id схемы
    user_attr_id = 3318 # id атрибута Jira User

    main(iql_query, scheme_id, user_attr_id=user_attr_id)