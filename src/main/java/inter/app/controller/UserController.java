package inter.app.controller;

import inter.app.model.SingleDigit;
import inter.app.model.User;
import inter.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="API REST")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value="Create user")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public HashMap<String, Object> store(@RequestBody User user) throws Exception {
        return this.service.create(user);
    }

    @ApiOperation(value="Find user")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User find(@PathVariable UUID id, @RequestHeader(name = "Authorization") String token) throws Exception {
        return this.service.find(id, token);
    }

    @ApiOperation(value="Edit user")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable UUID id, @RequestBody User user, @RequestHeader(name = "Authorization") String token) throws Exception {
        return this.service.update(id, user, token);
    }

    @ApiOperation(value="Delete user")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable UUID id) {
        this.service.delete(id);
    }

    @ApiOperation(value="Calculate digit root by user")
    @RequestMapping(value = "/user/{id}/calculate", method = RequestMethod.POST)
    public SingleDigit calculateByUser(@PathVariable UUID id, @RequestBody SingleDigit req) {
        return this.service.calculateByUser(id, req);
    }

    @ApiOperation(value="Calculate digit root")
    @RequestMapping(value = "/user/{id}/calculations", method = RequestMethod.GET)
    public List<SingleDigit> getUserCalculations(@PathVariable UUID id) {
        return this.service.getUserCalculations(id);
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public SingleDigit calculate(@RequestBody SingleDigit req) {
        return this.service.calculate(req);
    }
}
