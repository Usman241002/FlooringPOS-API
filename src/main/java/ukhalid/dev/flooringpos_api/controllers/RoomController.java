package ukhalid.dev.flooringpos_api.controllers;

import org.springframework.web.bind.annotation.*;
import ukhalid.dev.flooringpos_api.dtos.CreateRoomDTO;
import ukhalid.dev.flooringpos_api.entities.Room;
import ukhalid.dev.flooringpos_api.services.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room createRoom(@RequestBody CreateRoomDTO room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Integer id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public Room deleteRoom(@PathVariable Integer id) {
        return roomService.deleteRoom(id);
    }
}
