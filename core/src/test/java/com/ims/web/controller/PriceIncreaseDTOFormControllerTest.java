																								package com.ims.web.controller;

																								import com.ims.service.ProductService;
																								import com.ims.web.dto.PriceIncreaseDTO;
																								import org.junit.jupiter.api.Test;
																								import org.junit.jupiter.api.extension.ExtendWith;
																								import org.mockito.InjectMocks;
																								import org.mockito.Mock;
																								import org.mockito.junit.jupiter.MockitoExtension;

																								import java.util.Map;

																								import static org.junit.jupiter.api.Assertions.assertEquals;
																								import static org.junit.jupiter.api.Assertions.assertNotNull;
																								import static org.mockito.Mockito.doNothing;

																								@ExtendWith(MockitoExtension.class)
																								class PriceIncreaseDTOFormControllerTest {

																									@Mock
																									ProductService productService;

																									@InjectMocks
																									PriceIncreaseFormController controller;

																									@Test
																									void testDisplayAndSubmitApi() {
																										final var disp = controller.display();
																										assertNotNull(disp);
								final Map<String, Object> body = disp.getBody();
								assertNotNull(body);

																										final PriceIncreaseDTO cmd = new PriceIncreaseDTO();
																										cmd.setPercentage(10);
																										doNothing().when(productService).increasePrice(10);
																										final var resp = controller.onSubmitApi(cmd);
																										assertNotNull(resp);
																										assertEquals(200, resp.getStatusCode().value());
							}

						}
