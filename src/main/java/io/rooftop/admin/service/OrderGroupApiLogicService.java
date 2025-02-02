package io.rooftop.admin.service;

import io.rooftop.admin.dto.OrderType;
import io.rooftop.admin.entity.OrderGroup;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.request.OrderGroupApiRequestDto;
import io.rooftop.admin.network.response.OrderGroupApiResponseDto;
import io.rooftop.admin.repository.OrderGroupRepository;
import io.rooftop.admin.repository.UserRepository;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        return Optional.ofNullable(request.getData())
                .map(requestDto -> {
                    OrderGroup orderGroup = OrderGroup.builder()
                            .status(requestDto.getStatus())
                            .orderType(OrderType.ALL)
                            .revAddress(requestDto.getRevAddress())
                            .revName(requestDto.getRevName())
                            .paymentType(requestDto.getPaymentType())
                            .totalPrice(requestDto.getTotalPrice())
                            .totalQuantity(requestDto.getTotalQuantity())
                            .orderAt(LocalDateTime.now())
                            .user(userRepository.getOne(requestDto.getUserId()))
                            .build();
                    return orderGroup;
                })
                .map(orderGroup -> orderGroupRepository.save(orderGroup))
                .map(newOrderGroup -> response(newOrderGroup))
                .orElseGet(() -> Header.ERROR("No Data Found"));
    }

    @Override
    public Header<OrderGroupApiResponseDto> read(Long id) {
        return orderGroupRepository.findById(id)
                .map(orderGroup -> response(orderGroup))
                .orElseGet(() -> Header.ERROR("No Data Found"));
    }

    @Override
    public Header<OrderGroupApiResponseDto> update(Header<OrderGroupApiRequestDto> request) {
        return Optional.ofNullable(request.getData())
                .map(requestDto -> {
                    return orderGroupRepository.findById(requestDto.getId());
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(orderGroup -> {
                    OrderGroupApiRequestDto requestDto = request.getData();
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

    public Header<OrderGroupApiResponseDto> response(OrderGroup orderGroup) {
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


//=====================================
// for BaseService
//=====================================

//@RequiredArgsConstructor
//@Service
//public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequestDto, OrderGroupApiResponseDto, OrderGroup> {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public Header<OrderGroupApiResponseDto> create(Header<OrderGroupApiRequestDto> request) {
//        return Optional.ofNullable(request.getData())
//                .map(requestDto -> {
//                    OrderGroup orderGroup = OrderGroup.builder()
//                            .status(requestDto.getStatus())
//                            .orderType(OrderType.ALL)
//                            .revAddress(requestDto.getRevAddress())
//                            .revName(requestDto.getRevName())
//                            .paymentType(requestDto.getPaymentType())
//                            .totalPrice(requestDto.getTotalPrice())
//                            .totalQuantity(requestDto.getTotalQuantity())
//                            .orderAt(LocalDateTime.now())
//                            .user(userRepository.getOne(requestDto.getUserId()))
//                            .build();
//                    return orderGroup;
//                })
//                .map(orderGroup -> baseRepository.save(orderGroup))
//                .map(newOrderGroup -> response(newOrderGroup))
//                .orElseGet(() -> Header.ERROR("No Data Found"));
//    }
//
//    @Override
//    public Header<OrderGroupApiResponseDto> read(Long id) {
//        return baseRepository.findById(id)
//                .map(orderGroup -> response(orderGroup))
//                .orElseGet(() -> Header.ERROR("No Data Found"));
//    }
//
//    @Override
//    public Header<OrderGroupApiResponseDto> update(Header<OrderGroupApiRequestDto> request) {
//        return Optional.ofNullable(request.getData())
//                .map(requestDto -> {
//                    return baseRepository.findById(requestDto.getId());
//                })
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .map(orderGroup -> {
//                    OrderGroupApiRequestDto requestDto = request.getData();
//                    orderGroup
//                            .setStatus(requestDto.getStatus())
//                            .setOrderType(requestDto.getOrderType())
//                            .setRevAddress(requestDto.getRevAddress())
//                            .setRevName(requestDto.getRevName())
//                            .setPaymentType(requestDto.getPaymentType())
//                            .setTotalPrice(requestDto.getTotalPrice())
//                            .setTotalQuantity(requestDto.getTotalQuantity())
//                            .setOrderAt(LocalDateTime.now())
//                            .setUser(userRepository.getOne(requestDto.getUserId()))
//                    ;
//                    return orderGroup;
//                })
//                .map(orderGroup -> baseRepository.save(orderGroup))
//                .map(savedOrderGroup -> response(savedOrderGroup))
//                .orElseGet(() -> Header.ERROR("No Data Found"));
//    }
//
//    @Override
//    public Header delete(Long id) {
//        Optional<OrderGroup> optional = baseRepository.findById(id);
//
//        return optional.map(orderGroup -> {
//            baseRepository.delete(orderGroup);
//            return Header.OK();
//        }).orElseGet(() -> Header.ERROR("No Data Found"));
//    }
//
//    private Header<OrderGroupApiResponseDto> response(OrderGroup orderGroup) {
//        OrderGroupApiResponseDto responseDto = OrderGroupApiResponseDto.builder()
//                .id(orderGroup.getId())
//                .status(orderGroup.getStatus())
//                .orderType(orderGroup.getOrderType())
//                .revAddress(orderGroup.getRevAddress())
//                .revName(orderGroup.getRevName())
//                .paymentType(orderGroup.getPaymentType())
//                .totalPrice(orderGroup.getTotalPrice())
//                .totalQuantity(orderGroup.getTotalQuantity())
//                .orderAt(orderGroup.getOrderAt())
//                .arrivalDate(orderGroup.getArrivalDate())
//                .userId(orderGroup.getUser().getId())
//                .build();
//
//        return Header.OK(responseDto);
//    }
//}
