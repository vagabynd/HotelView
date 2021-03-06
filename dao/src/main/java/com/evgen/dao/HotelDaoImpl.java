package com.evgen.dao;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.evgen.Guest;
import com.evgen.ReservationRequest;
import com.evgen.connector.Connector;

@Component
public class HotelDaoImpl implements HotelDao {

  private Connector connector;

  @Value("${url.getHotels}")
  private String getHotelsUrl;

  @Value("${url.getGuestByName}")
  private String getGuestByNameUrl;

  @Value("${url.getReservations}")
  private String getReservationsUrl;

  @Value("${url.deleteReservation}")
  private String deleteReservationUrl;

  @Value("${url.createReservation}")
  private String createReservationUrl;

  @Value("${url.createGuest}")
  private String createGuestUrl;

  @Autowired
  public HotelDaoImpl(Connector connector) {
    this.connector = connector;
  }

  @Override
  public List getHotels() {
    URI uri = UriComponentsBuilder.fromUriString(getHotelsUrl).build().toUri();

    return connector.sendRequestWithoutBody(new HttpHeaders(), uri, HttpMethod.GET, ArrayList.class);
  }

  @Override
  public List getHotelByName(String name) {
    URI uri = UriComponentsBuilder.fromUriString(getHotelsUrl).queryParam("hotelName", name).build().toUri();

    return connector.sendRequestWithoutBody(new HttpHeaders(), uri, HttpMethod.GET, ArrayList.class);
  }

  @Override
  public Guest getGuestByName(String name) {
    URI uri = UriComponentsBuilder.fromUriString(getGuestByNameUrl).queryParam("name", name).build().toUri();

    return connector.sendRequestWithoutBody(new HttpHeaders(), uri, HttpMethod.GET, Guest.class);
  }

  @Override
  public List getReservations(String guestId) {
    URI uri = UriComponentsBuilder.fromUriString(getReservationsUrl).build().toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.add("guestId", guestId);

    return connector.sendRequestWithoutBody(headers, uri, HttpMethod.GET, ArrayList.class);
  }

  @Override
  public Guest deleteReservation(String guestId, String reservationId) {
    URI uri = UriComponentsBuilder.fromUriString(deleteReservationUrl).buildAndExpand(reservationId).toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.add("guestId", guestId);

    return connector.sendRequestWithoutBody(headers, uri, HttpMethod.DELETE, Guest.class);
  }

  @Override
  public Guest createReservation(ReservationRequest reservationRequest) {
    URI uri = UriComponentsBuilder.fromUriString(createReservationUrl).build().toUri();

    return connector.sendRequestWithBody(reservationRequest, new HttpHeaders(), uri, HttpMethod.POST, Guest.class);
  }

  @Override
  public Guest editReservation(ReservationRequest reservationRequest, String reservationId) {
    URI uri = UriComponentsBuilder.fromUriString(deleteReservationUrl).buildAndExpand(reservationId).toUri();

    return connector.sendRequestWithBody(reservationRequest, new HttpHeaders(), uri, HttpMethod.PUT, Guest.class);
  }

  @Override
  public Guest createGuest(Guest guest) {
    URI uri = UriComponentsBuilder.fromUriString(createGuestUrl).build().toUri();

    return connector.sendRequestWithBody(guest, new HttpHeaders(), uri, HttpMethod.POST, Guest.class);
  }

}