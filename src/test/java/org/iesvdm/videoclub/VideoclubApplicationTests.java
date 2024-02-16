package org.iesvdm.videoclub;

import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    PeliculaRepository peliculaRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testPeliCategoria(){


    }

}
