package com.qaminds.tests.home.menuOptions;

import com.qaminds.pages.Pages;
import com.qaminds.tests.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SearchAllTest extends BaseTest {

    @Test
    public void validateMenuOptionSearchAll(){
        log.info("Step 1: Validate the menu option: Search All");
        assertThat(Pages.returnHomePage(getDriver()).getTextOptionSearchAll()).isEqualTo("Buscar todo");
        log.info("Step 2: Click on the option Search All");
        Pages.returnHomePage(getDriver()).onClickOptionSearchAll();
        log.info("Step 3: Validate the text in the text field of search in Search All");
        String textSearchAll = Pages.returnHomePage(getDriver()).getTitleSearch(getDriver());
        assertThat(textSearchAll).isEqualTo("Lugares para visitar, cosas que hacer, hoteles...");
    }
}
