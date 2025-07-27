package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.exception.NotFoundException;
import br.edu.utfpr.dungeontable.model.User;
import br.edu.utfpr.dungeontable.model.vo.CampaignVO;
import br.edu.utfpr.dungeontable.model.vo.PlayerVO;
import br.edu.utfpr.dungeontable.model.vo.UserVO;
import br.edu.utfpr.dungeontable.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserVO> save(@RequestBody UserVO userVO){
        User user = modelMapper.map(userVO, User.class);

        userService.save(user);
        userVO.setId(user.getId());
        return new ResponseEntity<>(userVO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserVO> update(@PathVariable("id") Long id, @RequestBody UserVO userVO){
        User user = modelMapper.map(userVO, User.class);
        user.setId(id);
        userService.update(user);
        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a single user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404",  description = "Not found - The user was not found")
    })
    public ResponseEntity<UserVO> findById(@PathVariable("id") Long id) throws NotFoundException {
        User user = userService.findById(id);
        if (user == null) {
            throw new NotFoundException();
        }

        UserVO userVO = modelMapper.map(user, UserVO.class);
        userVO.setCampaigns(user.getCampaigns().stream().
                map(campaign -> modelMapper.map(campaign, CampaignVO.class)).
                toList());
        userVO.setPlayers(user.getPlayers().stream().
                map(player -> modelMapper.map(player, PlayerVO.class)).
                toList());

        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<UserVO>> findAll() {
        List<User> users = userService.findAll();
        List<UserVO> userVOs = users.stream().map(user -> modelMapper.map(user, UserVO.class)).
                toList();
        return new ResponseEntity<>(userVOs, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
