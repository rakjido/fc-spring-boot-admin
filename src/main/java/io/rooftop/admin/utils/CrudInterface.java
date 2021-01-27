package io.rooftop.admin.utils;

import io.rooftop.admin.network.Header;

public interface CrudInterface<Request, Response> {

    Header<Response> create(Header<Request> request);

    Header<Response> read(Long id);

    Header<Response> update(Header<Request> request);

    Header delete(Long id);
}
