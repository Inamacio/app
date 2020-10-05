package inter.app.service;

import inter.app.exception.BusinessException;
import inter.app.model.SingleDigit;
import inter.app.model.User;
import inter.app.repository.UserRepository;
import inter.app.util.Crypto;
import inter.app.util.DigitRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DigitRoot digitRoot;

    @Autowired
    private Crypto crypto;

    public HashMap<String, Object> create(User user) throws Exception {
        UUID id = UUID.randomUUID();

        KeyPair keyPair = this.crypto.generateKeyPair();

        user.setId(id);
        user.setEmail(this.crypto.encrypt(user.getEmail(), keyPair.getPublic()));
        user.setName(this.crypto.encrypt(user.getName(), keyPair.getPublic()));
        List<SingleDigit> calculations = new ArrayList<>();
        user.setCalculations(calculations);

        this.repository.save(id, user);

        HashMap<String, Object> map = new HashMap<>();
        map.put("publicKey", keyPair.getPublic().getEncoded());
        map.put("privateKey", keyPair.getPrivate().getEncoded());
        map.put("id", id);

        return map;
    }

    public User find(UUID id, String token) throws Exception {
        User user = this.repository.find(id);

        if (user == null) throw new BusinessException("User not found");

        if (token.isEmpty()) return user;

        byte[] privateKey = Base64.getDecoder().decode(token);

        user.setName(this.crypto.decrypt(user.getName(), privateKey));
        user.setEmail(this.crypto.decrypt(user.getEmail(), privateKey));
        user.setId(user.getId());

        return user;
    }

    public User update(UUID id, User changedUser, String token) throws Exception {
        User user = this.repository.find(id);

        if (user == null) throw new BusinessException("User not found");

        if (token.isEmpty()) throw new BusinessException("Public key headers is requerid");

        RSAPublicKey publicKey = this.crypto.getPublicKey(token);

        user.setName(this.crypto.encrypt(changedUser.getName(), publicKey));
        user.setEmail(this.crypto.encrypt(changedUser.getEmail(), publicKey));

        this.repository.save(id, user);

        return user;
    }

    public Object delete(UUID id) {
        User user = this.repository.find(id);

        if (user == null) throw new BusinessException("User not found");

        this.repository.delete(id);
        return null;
    }

    public SingleDigit calculate(SingleDigit single) {
        if (single.getInput() < 1 || single.getInput() > 100000) throw new BusinessException("Invalid input");

        return this.digitRoot.calculate(single.getInput(), single.getBase());
    }

    public SingleDigit calculateByUser(UUID id, SingleDigit single) {
        if (single.getInput() < 1 || single.getInput() > 100000) throw new BusinessException("Invalid input");

        User user = this.repository.find(id);

        if (user == null) throw new BusinessException("User not found");

        SingleDigit singleDigit = this.digitRoot.calculate(single.getInput(), single.getBase());

        List<SingleDigit> calculations = user.getCalculations();

        calculations.add(singleDigit);
        user.setCalculations(calculations);

        this.repository.save(id, user);

        return singleDigit;
    }

    public List<SingleDigit> getUserCalculations(UUID id) {
        User user = this.repository.find(id);

        if (user == null) throw new BusinessException("User not found");

        return user.getCalculations();
    }
}
