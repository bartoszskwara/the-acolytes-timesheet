package pl.lso.kazimierz.theacolytestimesheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.EventDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.PlaceDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;

import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheAcolytesTimesheetApplicationTests {

	@Test
	public void test() {

		PlaceDto p = PlaceDtoBuilder.getInstance()
				.withName("Church")
				.build();

		System.out.println(p.getName());



		EventDto e = EventDtoBuilder.getInstance()
				.withId(1L)
				.withPlace(p)
				.withStartDate(new Date())
				.build();

		assertThat(p).isNotNull();
		assertThat(p.getName()).isEqualTo("Church");
		assertThat(e.getPlace()).isNotNull();


	}

}
