package ru.netcracker.registration.model.converter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.DTO.SpotDTO;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.SpotInQuest;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.other.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для преобразования Entity пользователя в DTO пользователя
 */
public class QuestConverter {
    public static QuestDTO convertToDTO(Quest quest) {
        QuestDTO dto = new QuestDTO();

        dto.setName(quest.getName());
        dto.setDescription(quest.getDescription());
        dto.setReward(quest.getReward());
        dto.setUploadDate(quest.getUploadDate());
        dto.setPhotoURL(quest.getSpotInQuests().stream().findFirst().get().getPhotoByPhotoId().getUrl());
        dto.setLat(quest.getSpotInQuests().stream().findFirst().get().getSpotBySpotId().getLat().toString());
        dto.setLng(quest.getSpotInQuests().stream().findFirst().get().getSpotBySpotId().getLng().toString());
        List<SpotDTO> spots = new ArrayList<>();
        for(SpotInQuest spotInQuest: quest.getSpotInQuests()){
            spots.add(SpotConverter.convertToDTO(spotInQuest.getSpotBySpotId()));
        }
        dto.setSpots(spots);

        return dto;
    }

}
