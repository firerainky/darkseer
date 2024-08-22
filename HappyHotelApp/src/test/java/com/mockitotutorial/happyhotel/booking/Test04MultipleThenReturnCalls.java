package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test04MultipleThenReturnCalls {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    @BeforeEach
    void setup() {
        paymentServiceMock = mock(PaymentService.class);
        roomServiceMock = mock(RoomService.class);
        bookingDAOMock = mock(BookingDAO.class);
        mailSenderMock = mock(MailSender.class);

        bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    }

    @Test
    void should_CountAvailablePlaces_When_CalledMultipleTimes() {
        // given
        when(roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 5)))
                .thenReturn(Collections.emptyList());

        int expectedFirstCalled = 5;
        int expectedSecondCalled = 0;

        // when
        int actualFirst = bookingService.getAvailablePlaceCount();
        int actualSecond = bookingService.getAvailablePlaceCount();

        // then
        assertAll(
            () -> assertEquals(expectedFirstCalled, actualFirst),
            () -> assertEquals(expectedSecondCalled, actualSecond)
        );
    }

    @Test
    void should_CountAvailblePlaces_When_MultipleRoomsAvailable() {
        // given
        List<Room> rooms = Arrays.asList(new Room("Room 1", 2), new Room("Room 2", 5));
        when(roomServiceMock.getAvailableRooms()).thenReturn(rooms);

        int expected = 7;

        // when
        int actual = bookingService.getAvailablePlaceCount();

        // then
        assertEquals(expected, actual);
    }
}
