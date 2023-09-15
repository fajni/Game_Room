package com.project.game.user;

import com.project.game.pc.Pc;
import com.project.game.pc.PcRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PcRepository pcRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userNumber) {
        return userRepository.getReferenceById(userNumber);
    }

    public Optional<User> findSingleUserByUserNumber(Long userNumber) {
        return userRepository.findUserByNumber(userNumber);
    }

    public Optional<User> findSingleUserByPcNumber(Long numberPc) {
        return userRepository.findUserByPcNumber(numberPc);
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserNumber());
        List<User> userAll = userRepository.findAll();

        Optional<Pc> pcFind = pcRepository.findPcByNumber(user.getNumberPC());

        if (userOptional.isPresent())
            throw new IllegalStateException("User number " + user.getUserNumber() + " is already taken!");

        if (user.getNumberPC() == null)
            throw new IllegalStateException("User needs a pc number!");

        List<User> users = getUsers();
        for (int i = 0; i < users.size(); i++)
            if (Objects.equals(users.get(i).getName(), user.getName()) && Objects.equals(users.get(i).getLastname(), user.getLastname()))
                throw new IllegalStateException("User " + user.getName() + " " + user.getLastname() + " already exist!");

        for (int i = 0; i < userAll.size(); i++) {
            if (user.getNumberPC() == userAll.get(i).getNumberPC())
                throw new IllegalStateException("Pc with number " + user.getNumberPC() + " is already taken!");
        }

        //provera da li postoji racunar sa takvim number! Mozda da sam napravis bazu!
        if (pcFind.isEmpty())
            throw new IllegalStateException("Pc with number " + user.getNumberPC() + " does not exist!");

        //dodavanje novog racunara (azuriranje) pc statusa na ON ukoliko je user.userNumber = pc.userNumber
        pcFind.get().setStatus("ON");
        pcFind.get().setUserNumber(user.getUserNumber());

        userRepository.save(user);
        System.out.println("Added user: " + user.getName() + " " + user.getLastname());
    }

    public void deleteUser(Long userNumber) {
        Optional<User> userOptional = userRepository.findById(userNumber);
        Optional<Pc> pcFind = pcRepository.findPcByNumber(userOptional.get().getNumberPC());

        if (userNumber == null)
            throw new IllegalStateException("Value can't be null!");

        if (!userRepository.existsById(userNumber))
            throw new IllegalStateException("User with number " + userNumber + " does not exist!");

        //ukoliko se obrise user polja u pc se postavljaju na OFF i null
        pcFind.get().setStatus("OFF");
        pcFind.get().setUserNumber(null);

        userRepository.deleteById(userNumber);
        System.out.println("Removed user: " + userOptional.get().getName() + " " + userOptional.get().getLastname());
    }

    @Transactional
    public void updateUser(Long userNumber, String name, String lastname, Long numberPC) {
        User user = userRepository.findUserByNumber(userNumber)
                .orElseThrow(() -> new IllegalStateException("User with number " + userNumber + " doesn't exist!"));
        Pc currentPc = pcRepository.findPcByNumber(user.getNumberPC()).get(); //trenutno
        Pc pc = pcRepository.findPcByNumber(numberPC)
                .orElseThrow(() -> new IllegalStateException("Pc with number " + numberPC + " does not exist!")); //prosledjeni
        List<User> users = userRepository.findAll();

        //ukoliko korisnik postoji sa istim name i lastname, nije dobro
        for(int i=0;i<users.size();i++){
            if(Objects.equals(users.get(i).getName(), name) &&
                    Objects.equals(users.get(i).getLastname(), lastname) &&
                    !Objects.equals(users.get(i).getName(), user.getName()) &&
                    !Objects.equals(users.get(i).getLastname(), lastname))
                throw new IllegalStateException("User "+name+" "+lastname+" already exist!");
        }

        //ukoliko se prosledi prazna imena
        if (name.isEmpty() || name.isBlank())
            throw new IllegalStateException("User must have a name!");
        if (lastname.isEmpty() || lastname.isBlank())
            throw new IllegalStateException("User must have a lastname!");
        if (numberPC.toString().isBlank() || numberPC.toString().isEmpty())
            throw new IllegalStateException("User must have a PC number!");

        if(pc.getPcNumber() == currentPc.getPcNumber())
            user.setNumberPC(numberPC);

        //ukoliko pc nije null znaci da ima korisnika
        if(!Objects.equals(pc.getUserNumber(),null) && pc.getPcNumber() != currentPc.getPcNumber())
            throw new IllegalStateException("Pc "+pc.getPcNumber()+" already has a user!");
        user.setNumberPC(numberPC);

        if(!Objects.equals(name, null) && (!name.isBlank() || !name.isEmpty()))
            user.setName(name);
        if(!Objects.equals(lastname, null) && (!lastname.isBlank() || !lastname.isEmpty()))
            user.setLastname(lastname);

        currentPc.setUserNumber(null);
        currentPc.setStatus("OFF");

        pc.setUserNumber(userNumber);
        pc.setStatus("ON");
    }
}
