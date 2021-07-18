package kitchenpos.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kitchenpos.menu.application.MenuGroupService;
import kitchenpos.menu.dto.MenuGroupRequest;
import kitchenpos.menu.dto.MenuGroupResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuGroupRestController.class)
class MenuGroupRestControllerTest {
	private static final String BASE_URL = "/api/menu-groups";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MenuGroupRestController menuGroupRestController;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private MenuGroupService menuGroupService;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(menuGroupRestController)
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.alwaysDo(print())
				.build();
	}

	@Test
	void createTest() throws Exception {
		MenuGroupRequest menuGroupRequest = new MenuGroupRequest("치킨");
		MenuGroupResponse aMenuGroupResponse = new MenuGroupResponse(1L, "치킨");
		given(menuGroupService.create(menuGroupRequest)).willReturn(aMenuGroupResponse);

		mockMvc.perform(
				post(BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(aMenuGroupResponse)))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", BASE_URL + "/1"));
	}

	@Test
	void listTest() throws Exception {
		List<MenuGroupResponse> menuGroupRespons = Lists.list(new MenuGroupResponse(1L, "치킨"), new MenuGroupResponse(2L, "피자"));
		given(menuGroupService.list()).willReturn(menuGroupRespons);

		mockMvc.perform(
				get(BASE_URL).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("치킨")));
	}
}