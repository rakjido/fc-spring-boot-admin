package io.rooftop.admin.service;

import io.rooftop.admin.entity.OrderGroup;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.request.OrderGroupApiRequestDto;
import io.rooftop.admin.network.response.OrderGroupApiResponseDto;
import io.rooftop.admin.repository.OrderGroupRepository;
import io.rooftop.admin.repository.UserRepository;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequestDto, OrderGroupApiResponseDto> {

    private final OrderGroupRepository orderGroupRepository;

    private final UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponseDto> create(Header<OrderGroupApiRequestDto> request) {
        OrderGroupApiRequestDto requestDto = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .status(requestDto.getStatus())
                .orderType(requestDto.getOrderType())
                .revAddress(requestDto.getRevAddress())
                .revName(requestDto.getRevName())
                .paymentType(requestDto.getPaymentType())
                .totalPrice(requestDto.getTotalPrice())
                .totalQuantity(requestDto.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(requestDto.getUserId()))
                .build();
        OrderGroup savedOrderGroup = orderGroupRepository.save(orderGroup);
        return response(savedOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponseDto> read(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("No Data Found"));
    }

    @Override
    public Header<OrderGroupApiResponseDto> update(Header<OrderGroupApiRequestDto> request) {
        OrderGroupApiRequestDto requestDto = request.getData();
        Optional<OrderGroup> optional = orderGroupRepository.findById(requestDto.getId());

        return optional.map(orderGroup -> {
            orderGroup
                    .setStatus(requestDto.getStatus())
                    .setOrderType(requestDto.getOrderType())
                    .setRevAddress(requestDto.getRevAddress())
                    .setRevName(requestDto.getRevName())
                    .setPaymentType(requestDto.getPaymentType())
                    .setTotalPrice(requestDto.getTotalPrice())
                    .setTotalQuantity(requestDto.getTotalQuantity())
                    .setOrderAt(LocalDateTime.now())
                    .setUser(userRepository.getOne(requestDto.getUserId()))
            ;
            return orderGroup;
        })
        .map(orderGroup -> orderGroupRepository.save(orderGroup))
        .map(savedOrderGroup -> response(savedOrderGroup))
        .orElseGet(() -> Header.ERROR("No Data Found"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional.map(orderGroup -> {
            orderGroupRepository.delete(orderGroup);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("No Data Found"));
    }

    private Header<OrderGroupApiResponseDto> response(OrderGroup orderGroup) {
        OrderGroupApiResponseDto responseDto = OrderGroupApiResponseDto.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(responseDto);
    }
}
