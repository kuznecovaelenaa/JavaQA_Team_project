package ru.netology;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStoreTest {
    GameStore store = new GameStore();


    @Test
/** Проверка добавления игры 1 игры
 *
 * тест не проходит
 */ public void shouldAddGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test
    /** Проверка добавления всех 3х игр
     *
     * тест НЕ проходит. Ошибка в цикле метода containsGame
     */ public void shouldAddGame1() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
    }

    @Test
/** Проверка добавления игры 1 игры из 1ой в каталоге
 *
 * тест НЕ проходит. Ошибка в цикле метода containsGame
 */ public void shouldAddGame2() {

        Game game3 = store.publishGame("AAAA", "BBB");
        assertTrue(store.containsGame(game3));

    }

    @Test
/** провека метода publishGame на добавление игры с одним и тем же названием
 *
 * тест НЕ проходит. должно быть исключение, так как это одинаковые игры
 */ public void addGame() {

        List<Game> expected = new ArrayList<>();
        expected.add(new Game("Нетология Баттл Онлайн", "Аркады", store));
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertEquals(expected, store.getGames());

    }

    @Test
    public void playTime() {
/** проверка добавления времени игры к игроку, при 1ом запуске игры
 *
 * тест не проходит. getSumPlayedTime должен считать общее время, но не считает - а возвращает 0)
 */

        store.addPlayTime("Ivan", 1);

        assertEquals(1, store.getSumPlayedTime());
    }

    @Test
    public void playTime1() {
/** проверка добавления времени игры к 2 игрокам, при 1om запуске игры
 *
 * тест не проходит. getSumPlayedTime должен считать общее время, но не считает - а возвращает 0)
 */

        store.addPlayTime("Ivan", 1);
        store.addPlayTime("Anna", 1);

        assertEquals(2, store.getSumPlayedTime());
    }

    @Test
    public void playTime2() {

        /** проверка добавления времени игры к игроку, если игрок уже играл 1 час
         *
         * тест не проходит. getSumPlayedTime должен считать общее время, но не считает - а возвращает 0)
         */

        store.addPlayTime("Anna", 1);
        store.addPlayTime("Anna", 3);

        assertEquals(4, store.getSumPlayedTime());

    }

    @Test
    public void playTime3() {

        /** проверка добавления времени игры к 2 игрокам, если игроки уже играли 1 час
         *
         * тест не проходит. getSumPlayedTime должен считать общее время, но не считает - а возвращает 0)
         */

        store.addPlayTime("Anna", 1);
        store.addPlayTime("Fedor", 1);
        store.addPlayTime("Anna", 3);
        store.addPlayTime("Fedor", 2);

        assertEquals(7, store.getSumPlayedTime());

    }

    @Test
    public void playTime5() {

        /** проверка добавления -1 часа игры (невалидное значение)
         *
         * тест не проходит. addPlayTime не должен принимать отрицательные значения времени игры)
         */

        store.addPlayTime("Fedor", -10);

        assertEquals(0, store.getSumPlayedTime());

    }

    @Test
    public void bestPlayer() {
        /** проверка поиска игроков по времени игры
         * проверка 1: нет игроков - возврать налл
         *
         * тест проходит
         */
        store.getMostPlayer();
        assertNull(store.getMostPlayer(), "null");
    }

    @Test
    public void bestPlayer1() {
        /** проверка поиска игроков по времени игры
         * проверка 1: 1 игрок - возврат игрока
         *
         * тест проходит
         */
        store.addPlayTime("Ivan", 10);
        assertEquals("Ivan", store.getMostPlayer());
    }

    @Test
    public void bestPlayer2() {
        /** проверка поиска игроков по времени игры, если играл всего 1 час
         * проверка 1: 1 игрок - возврат игрока
         *
         * тест НЕ проходит, так как в методе установлен знак > 1
         */
        store.addPlayTime("Ivan", 1);
        assertEquals("Ivan", store.getMostPlayer());
    }

    @Test
    public void bestPlayer3() {
        /** проверка поиска игроков по времени игры
         * проверка 2: 2 игрока - возврат лучшего
         *
         * тест проходит
         */
        store.addPlayTime("Ivan", 2);
        store.addPlayTime("Anna", 4);

        assertEquals("Anna", store.getMostPlayer());
    }

    @Test
    public void bestPlayer4() {
        /** проверка поиска игроков по времени игры
         *  3 игрока - возврат лучшего
         *
         * тест проходит
         */
        store.addPlayTime("Ivan", 4);
        store.addPlayTime("Fedor", 10);
        store.addPlayTime("Anna", 9);

        assertEquals("Fedor", store.getMostPlayer());
    }


    @Test
    public void sumPlayedTime() {
        /** проверка суммирования времени игры
         *
         * тест не проходит. в методе нет сложения
         */

        store.addPlayTime("Ivan", 10);
        store.addPlayTime("Fedor", 10);
        store.addPlayTime("Anna", 10);

        assertEquals(30, store.getSumPlayedTime());
    }
}
