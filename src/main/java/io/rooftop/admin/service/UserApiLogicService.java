package io.rooftop.admin.service;

import io.rooftop.admin.dto.OrderType;
import io.rooftop.admin.dto.UserStatus;
import io.rooftop.admin.entity.OrderGroup;
import io.rooftop.admin.entity.User;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.request.OrderGroupApiRequestDto;
import io.rooftop.admin.network.request.UserApiRequestDto;
import io.rooftop.admin.network.response.UserApiResponseDto;
import io.rooftop.admin.repository.UserRepository;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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


@Slf4j
@RequiredArgsConstructor
@Service
public class UserApiLogicService implements CrudInterface<UserApiRequestDto, UserApiResponseDto> {

    private final UserRepository userRepository;

    @Override
    public Header<UserApiResponseDto> create(Header<UserApiRequestDto> requestDto) {
        UserApiRequestDto userApiRequestDto = requestDto.getData();
        log.info(userApiRequestDto.toString());
        User user = User.builder()
                .account(userApiRequestDto.getAccount())
                .password(userApiRequestDto.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequestDto.getPhoneNumber())
                .email(userApiRequestDto.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);
        return response(newUser);
    }

    @Override
    public Header<UserApiResponseDto> read(Long id) {
        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(()-> Header.ERROR("No Data found"));
    }

    @Override
    public Header<UserApiResponseDto> update(Header<UserApiRequestDto> requestDto) {
        UserApiRequestDto userApiRequestDto = requestDto.getData();
        Optional<User> optional = userRepository.findById(userApiRequestDto.getId());

        return optional.map(user -> {
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
                .orElseGet(()->Header.ERROR("No Data Found"));

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

    private Header<UserApiResponseDto> response(User user){

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

        return Header.OK(userApiResponseDto);
    }
}
