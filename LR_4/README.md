### Задание
Разработать модульные тесты для заданного метода сортировки, реализованного в л.р. 1-3

Последовательность выполнения:
- Структурное тестирование: разработать тестовое покрытие для тестирования операторов, условий и путей
- Написать программу тестирования в JUnit, исполняющую весь тестовый набор, фиксирующую нормальное и ошибочное выполнение каждого теста и выводящую несовпадения
- Внести 2-3 дефекта и проверить тестовое покрытие
- Функциональное тестирование: определить тестируемые функциональные свойства и их граничные значения на основе свойств данных, функционального назначения алгоритма и возможных ошибок программирования. Построить тестовый набор
- Написать программу тестирования в JUnit, исполняющую весь тестовый набор, фиксирующую нормальное и ошибочное выполнение каждого теста и выводящую несовпадения
- Внести 2-3 дефекта и проверить тестовое покрытие
- Тестирование полупрозрачного ящика. Определить дополнительные тестируемые свойства на основе анализа (инспекции) программы. Построить тестовый набор. Дополнить функциональные тесты.
- Внести дефекты, связанные с особенностями реализации и проверить их обнаружение тестами

Модульные тесты должны проверять корректность исполнения для перечисленных тестовых случаев 
- исходный набор содержит одинаковые значения
- исходный набор неупорядочен 
- исходный набор упорядочен в прямом порядке
- исходный набор упорядочен в обратном порядке
- в наборе имеются повторяющиеся элементы
- в наборе имеются несколько групп повторяющихся элементов
- экстремальное значение находится в середине набора
- экстремальное значение находится в начале набора
- экстремальное значение находится в конце набора
- в наборе несколько совпадающих экстремальных значений