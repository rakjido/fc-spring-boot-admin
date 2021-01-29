package io.rooftop.admin.service;

import io.rooftop.admin.dto.ItemStatus;
import io.rooftop.admin.dto.OrderType;
import io.rooftop.admin.dto.UserStatus;
import io.rooftop.admin.entity.Item;
import io.rooftop.admin.entity.OrderGroup;
import io.rooftop.admin.entity.User;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.Pagination;
import io.rooftop.admin.network.request.OrderGroupApiRequestDto;
import io.rooftop.admin.network.request.UserApiRequestDto;
import io.rooftop.admin.network.response.ItemApiResponseDto;
import io.rooftop.admin.network.response.OrderGroupApiResponseDto;
import io.rooftop.admin.network.response.UserApiResponseDto;
import io.rooftop.admin.network.response.UserOrderInfoApiResponseDto;
import io.rooftop.admin.repository.OrderGroupRepository;
import io.rooftop.admin.repository.UserRepository;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserApiLogicService implements CrudInterface<UserApiRequestDto, UserApiResponseDto> {

    private final UserRepository userRepository;

    @Override
    public Header<UserApiResponseDto> create(Header<UserApiRequestDto> requestDto) {

        return Optional.ofNullable(requestDto.getData())
                .map(userApiRequestDto -> {
                    User user = User.builder()
                            .account(userApiRequestDto.getAccount())
                            .password(userApiRequestDto.getPassword())
                            .status(UserStatus.REGISTERED)
                            .phoneNumber(userApiRequestDto.getPhoneNumber())
                            .email(userApiRequestDto.getEmail())
                            .registeredAt(LocalDateTime.now())
                            .build();
                    return user;
                })
                .map(user -> userRepository.save(user))
                .map(newUser -> Header.OK(response(newUser)))
                .orElseGet(()->Header.ERROR("No Data Found"));
    }

    @Override
    public Header<UserApiResponseDto> read(Long id) {
        return userRepository.findById(id)
                .map(user -> response(user))
//                .map(userApiResponseDto -> Header.OK(userApiResponseDto))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("No Data found"));
    }

    @Override
    public Header<UserApiResponseDto> update(Header<UserApiRequestDto> requestDto) {

        return Optional.ofNullable(requestDto.getData())
                .map(userApiRequestDto -> {
                    return userRepository.findById(userApiRequestDto.getId());
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    UserApiRequestDto userApiRequestDto = requestDto.getData();
                    user.setAccount(userApiRequestDto.getAccount())
                            .setPassword(userApiRequestDto.getPassword())
                            .setPhoneNumber(userApiRequestDto.getPhoneNumber())
                            .setEmail(userApiRequestDto.getEmail())
                            .setStatus(userApiRequestDto.getStatus())
                            .setRegisteredAt(userApiRequestDto.getRegisteredAt())
                            .setUnregisteredAt(userApiRequestDto.getUnregisteredAt())
                    ;
                    return user;
                })
                .map(user -> userRepository.save(user))
                .map(updatedUser -> response(updatedUser))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("No Data Found"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = userRepository.findById(id);

        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(()->Header.ERROR("No Data Found"));

    }

    public Header<List<UserApiResponseDto>> search(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserApiResponseDto> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList, pagination);
    }

    public Header<UserOrderInfoApiResponseDto> orderInfo(Long id) {
        // user
        User user = userRepository.getOne(id);
        UserApiResponseDto userApiResponse = response(user);

        // orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponseDto> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponseDto orderGroupApiResponse = response(orderGroup).getData();

                    // item api response
                    List<ItemApiResponseDto> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(orderDetail -> orderDetail.getItem())
                            .map(item -> response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
        UserOrderInfoApiResponseDto userOrderInfoApiResponse = UserOrderInfoApiResponseDto.builder()
                .userApiResponse(userApiResponse)
                .build();
        return Header.OK(userOrderInfoApiResponse);
    }

    private UserApiResponseDto response(User user){

        UserApiResponseDto userApiResponseDto = UserApiResponseDto.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이 변환 등
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return userApiResponseDto;
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

    private Header<ItemApiResponseDto> response(Item item) {
        ItemApiResponseDto itemApiResponseDto = ItemApiResponseDto.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(itemApiResponseDto);
    }

}


//=====================================
// for BaseService
//=====================================

//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class UserApiLogicService implements CrudInterface<UserApiRequestDto, UserApiResponseDto> {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public Header<UserApiResponseDto> create(Header<UserApiRequestDto> requestDto) {
//        return Optional.ofNullable(requestDto.getData())
//                .map(userApiRequestDto -> {
//                    User user = User.builder()
//                            .account(userApiRequestDto.getAccount())
//                            .password(userApiRequestDto.getPassword())
//                            .status(UserStatus.REGISTERED)
//                            .phoneNumber(userApiRequestDto.getPhoneNumber())
//                            .email(userApiRequestDto.getEmail())
//                            .registeredAt(LocalDateTime.now())
//                            .build();
//                    return user;
//                })
//                .map(user -> userRepository.save(user))
//                .map(newUser -> response(newUser))
//                .orElseGet(() -> Header.ERROR("No Data Found"));
//
////
////        UserApiRequestDto userApiRequestDto = requestDto.getData();
////        log.info(userApiRequestDto.toString());
////        User user = User.builder()
////                    .account(userApiRequestDto.getAccount())
////                    .password(userApiRequestDto.getPassword())
////                    .status(UserStatus.REGISTERED)
////                    .phoneNumber(userApiRequestDto.getPhoneNumber())
////                    .email(userApiRequestDto.getEmail())
////                    .registeredAt(LocalDateTime.now())
////                    .build();
////
////        User newUser = userRepository.save(user);
////        return response(newUser);
//    }
//
//    @Override
//    public Header<UserApiResponseDto> read(Long id) {
//        return userRepository.findById(id)
//                .map(user -> response(user))
//                .orElseGet(()-> Header.ERROR("No Data found"));
//    }
//
//    @Override
//    public Header<UserApiResponseDto> update(Header<UserApiRequestDto> requestDto) {
//        return Optional.ofNullable(requestDto.getData())
//                .map(userApiRequestDto -> {
//                    return userRepository.findById(userApiRequestDto.getId());
//                })
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .map(user -> {
//                    UserApiRequestDto userApiRequestDto = requestDto.getData();
//                    user.setAccount(userApiRequestDto.getAccount())
//                            .setPassword(userApiRequestDto.getPassword())
//                            .setPhoneNumber(userApiRequestDto.getPhoneNumber())
//                            .setEmail(userApiRequestDto.getEmail())
//                            .setStatus(userApiRequestDto.getStatus())
//                            .setRegisteredAt(userApiRequestDto.getRegisteredAt())
//                            .setUnregisteredAt(userApiRequestDto.getUnregisteredAt())
//                    ;
//                    return user;
//                })
//                .map(user -> userRepository.save(user))
//                .map(newUser -> response(newUser))
//                .orElseGet(() -> Header.ERROR("No Data Found"));
//
////
////        UserApiRequestDto userApiRequestDto = requestDto.getData();
////        Optional<User> optional = userRepository.findById(userApiRequestDto.getId());
////
////        return optional.map(user -> {
////                user.setAccount(userApiRequestDto.getAccount())
////                        .setPassword(userApiRequestDto.getPassword())
////                        .setPhoneNumber(userApiRequestDto.getPhoneNumber())
////                        .setEmail(userApiRequestDto.getEmail())
////                        .setStatus(userApiRequestDto.getStatus())
////                        .setRegisteredAt(userApiRequestDto.getRegisteredAt())
////                        .setUnregisteredAt(userApiRequestDto.getUnregisteredAt())
////                        ;
////            return user;
////        })
////        .map(user -> userRepository.save(user))
////        .map(updatedUser -> response(updatedUser))
////        .orElseGet(()->Header.ERROR("No Data Found"));
//
//    }
//
//    @Override
//    public Header delete(Long id) {
//        Optional<User> optional = userRepository.findById(id);
//
//        return optional.map(user -> {
//                    userRepository.delete(user);
//                    return Header.OK();
//                })
//                .orElseGet(()->Header.ERROR("No Data Found"));
//
//    }
//
//    private Header<UserApiResponseDto> response(User user){
//
//        UserApiResponseDto userApiResponseDto = UserApiResponseDto.builder()
//                .id(user.getId())
//                .account(user.getAccount())
//                .password(user.getPassword()) // todo 암호화, 길이 변환 등
//                .email(user.getEmail())
//                .phoneNumber(user.getPhoneNumber())
//                .status(user.getStatus())
//                .registeredAt(user.getRegisteredAt())
//                .unregisteredAt(user.getUnregisteredAt())
//                .build();
//
//        return Header.OK(userApiResponseDto);
//    }
//}
