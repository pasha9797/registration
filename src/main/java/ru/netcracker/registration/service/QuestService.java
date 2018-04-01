package ru.netcracker.registration.service;


import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.DTO.UserProgressDTO;
import ru.netcracker.registration.model.Photo;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.User;

import java.util.List;

public interface QuestService {
    QuestDTO getById(Long id);
    List<Quest> getByName(String name);
    QuestDTO getOneByName(String name);
    List<Quest> getAll();
    boolean delete(Long id);
    boolean delete(Quest quest);
    Quest save(Quest quest);
    void save(QuestDTO questDTO, Photo photo, User user);
    void save(QuestDTO questDTO, User user);
    List<QuestDTO> getAllToDTO();
    List<QuestDTO> getAllInRange(double lat, double lng, double range);
    List<UserProgressDTO> getUserProgressByUser(String email);
    UserProgressDTO getUserProgressByUserAndQuest(String email, Long questId);
    void userJoinQuest(String email, Long questId);
}
