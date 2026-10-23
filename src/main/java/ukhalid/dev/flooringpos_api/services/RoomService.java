package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.dtos.CreateRoomDTO;
import ukhalid.dev.flooringpos_api.entities.Address;
import ukhalid.dev.flooringpos_api.entities.Room;
import ukhalid.dev.flooringpos_api.repositories.AddressRepository;
import ukhalid.dev.flooringpos_api.repositories.RoomRepository;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;

    public RoomService(RoomRepository roomRepository, AddressRepository addressRepository) {
        this.roomRepository = roomRepository;
        this.addressRepository = addressRepository;
    }

    public Room createRoom(CreateRoomDTO room) {

        Optional<Address> addressOptional = addressRepository.findById(room.getAddressId());

        if (addressOptional.isEmpty()) {
            throw new IllegalArgumentException("Address not found");
        }

        Room newRoom = new Room(
                addressOptional.get(),
                room.getName(),
                room.getLength(),
                room.getWidth()
        );

        return roomRepository.save(newRoom);
    }

    public Room updateRoom(Integer id, Room room) {
        Optional<Room> roomToUpdateOptional = this.roomRepository.findById(id);

        if (roomToUpdateOptional.isEmpty()) {
            throw new IllegalArgumentException("Room not found");
        }

        Room roomToUpdate = roomToUpdateOptional.get();
        roomToUpdate.setName(room.getName());
        roomToUpdate.setLength(room.getLength());
        roomToUpdate.setWidth(room.getWidth());

        return roomRepository.save(roomToUpdate);
    }

    public void deleteRoom(Integer id) {
        Optional<Room> roomToDeleteOptional = roomRepository.findById(id);

        if (roomToDeleteOptional.isEmpty()) {
            throw new IllegalArgumentException("Room not found");
        }
        Room roomToDelete = roomToDeleteOptional.get();
        roomRepository.delete(roomToDelete);
    }
}
