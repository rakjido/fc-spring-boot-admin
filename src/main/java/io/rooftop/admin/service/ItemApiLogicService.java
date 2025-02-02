package io.rooftop.admin.service;

import io.rooftop.admin.dto.ItemStatus;
import io.rooftop.admin.entity.Item;
import io.rooftop.admin.network.Header;
import io.rooftop.admin.network.request.ItemApiRequestDto;
import io.rooftop.admin.network.request.OrderGroupApiRequestDto;
import io.rooftop.admin.network.response.ItemApiResponseDto;
import io.rooftop.admin.repository.ItemRepository;
import io.rooftop.admin.repository.PartnerRepository;
import io.rooftop.admin.utils.CrudInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequestDto, ItemApiResponseDto> {

    private final ItemRepository itemRepository;

    private final PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponseDto> create(Header<ItemApiRequestDto> request) {
        return Optional.ofNullable(request.getData())
        .map(itemApiRequestDto -> {
            Item item = Item.builder()
                    .status(ItemStatus.WAITING)
                    .name(itemApiRequestDto.getName())
                    .title(itemApiRequestDto.getTitle())
                    .content(itemApiRequestDto.getContent())
                    .price(itemApiRequestDto.getPrice())
                    .brandName(itemApiRequestDto.getBrandName())
                    .registeredAt(LocalDateTime.now())
                    .partner(partnerRepository.getOne(itemApiRequestDto.getPartnerId()))
                    .build();
            return item;
        })
        .map(item -> itemRepository.save(item))
        .map(newItem -> response(newItem))
        .orElseGet(()->Header.ERROR("No Data Found"));
    }

    @Override
    public Header<ItemApiResponseDto> read(Long id) {
        return itemRepository.findById(id)
                .map(item -> response(item))
                .orElseGet(() -> Header.ERROR("No Data Found"));
    }

    @Override
    public Header<ItemApiResponseDto> update(Header<ItemApiRequestDto> request) {

        return Optional.ofNullable(request.getData())
                .map(requestDto -> {
                    return itemRepository.findById(requestDto.getId());
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(item -> {
                    ItemApiRequestDto itemApiRequestDto = request.getData();
                    item
                            .setStatus(itemApiRequestDto.getStatus())
                            .setName(itemApiRequestDto.getName())
                            .setTitle(itemApiRequestDto.getTitle())
                            .setContent(itemApiRequestDto.getContent())
                            .setPrice(itemApiRequestDto.getPrice())
                            .setBrandName(itemApiRequestDto.getBrandName())
                            .setRegisteredAt(LocalDateTime.now())
                            .setPartner(partnerRepository.getOne(itemApiRequestDto.getPartnerId()))
                    ;
                    return item;
                })
                .map(item -> itemRepository.save(item))
                .map(savedItem -> response(savedItem))
                .orElseGet(() -> Header.ERROR("No Data Found"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        return optional.map(item -> {
            itemRepository.delete(item);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("No Data Found"));
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
//@RequiredArgsConstructor
//@Service
//public class ItemApiLogicService extends BaseService<ItemApiRequestDto, ItemApiResponseDto, Item> {
//
//    private final PartnerRepository partnerRepository;
//
//    @Override
//    public Header<ItemApiResponseDto> create(Header<ItemApiRequestDto> request) {
//        return Optional.ofNullable(request.getData())
//                .map(itemApiRequestDto -> {
//                    Item item = Item.builder()
//                            .status(ItemStatus.WAITING)
//                            .name(itemApiRequestDto.getName())
//                            .title(itemApiRequestDto.getTitle())
//                            .content(itemApiRequestDto.getContent())
//                            .price(itemApiRequestDto.getPrice())
//                            .brandName(itemApiRequestDto.getBrandName())
//                            .registeredAt(LocalDateTime.now())
//                            .partner(partnerRepository.getOne(itemApiRequestDto.getPartnerId()))
//                            .build();
//                    return item;
//                })
//                .map(item -> baseRepository.save(item))
//                .map(newItem -> response(newItem))
//                .orElseGet(()->Header.ERROR("No Data Found"));
//
////        ItemApiRequestDto itemApiRequestDto = request.getData();
////        Item item = Item.builder()
////                .status(ItemStatus.WAITING)
////                .name(itemApiRequestDto.getName())
////                .title(itemApiRequestDto.getTitle())
////                .content(itemApiRequestDto.getContent())
////                .price(itemApiRequestDto.getPrice())
////                .brandName(itemApiRequestDto.getBrandName())
////                .registeredAt(LocalDateTime.now())
////                .partner(partnerRepository.getOne(itemApiRequestDto.getPartnerId()))
////                .build();
////        Item savedItem = baseRepository.save(item);
////        return response(savedItem);
//    }
//
//    @Override
//    public Header<ItemApiResponseDto> read(Long id) {
//        return baseRepository.findById(id)
//                .map(item -> response(item))
//                .orElseGet(() -> Header.ERROR("No Data Found"));
//    }
//
//    @Override
//    public Header<ItemApiResponseDto> update(Header<ItemApiRequestDto> request) {
//        ItemApiRequestDto itemApiRequestDto = request.getData();
//        Optional<Item> optional = baseRepository.findById(itemApiRequestDto.getId());
//
//        return optional.map(item -> {
//                item
//                    .setStatus(itemApiRequestDto.getStatus())
//                    .setName(itemApiRequestDto.getName())
//                    .setTitle(itemApiRequestDto.getTitle())
//                    .setContent(itemApiRequestDto.getContent())
//                    .setPrice(itemApiRequestDto.getPrice())
//                    .setBrandName(itemApiRequestDto.getBrandName())
//                    .setRegisteredAt(LocalDateTime.now())
//                    .setPartner(partnerRepository.getOne(itemApiRequestDto.getPartnerId()));
//            return item;
//        }).map(item -> baseRepository.save(item))
//        .map(savedItem -> response(savedItem))
//        .orElseGet(() -> Header.ERROR("No Data Found"));
//    }
//
//    @Override
//    public Header delete(Long id) {
//        Optional<Item> optional = baseRepository.findById(id);
//        return optional.map(item -> {
//            baseRepository.delete(item);
//            return Header.OK();
//        })
//                .orElseGet(() -> Header.ERROR("No Data Found"));
//    }
//
//    private Header<ItemApiResponseDto> response(Item item) {
//        ItemApiResponseDto itemApiResponseDto = ItemApiResponseDto.builder()
//                .id(item.getId())
//                .status(item.getStatus())
//                .name(item.getName())
//                .title(item.getTitle())
//                .content(item.getContent())
//                .price(item.getPrice())
//                .brandName(item.getBrandName())
//                .registeredAt(item.getRegisteredAt())
//                .unregisteredAt(item.getUnregisteredAt())
//                .partnerId(item.getPartner().getId())
//                .build();
//
//        return Header.OK(itemApiResponseDto);
//    }
//
//}
