package ru.chepusov.NauJava;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.chepusov.NauJava.repository.ReportRepository;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private ReportRepository reportRep;

    @InjectMocks
    private ReportServiceTest rst;



}
