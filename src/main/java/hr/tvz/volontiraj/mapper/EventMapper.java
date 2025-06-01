package hr.tvz.volontiraj.mapper;

import hr.tvz.volontiraj.dto.EventDto;
import hr.tvz.volontiraj.model.Event;
import hr.tvz.volontiraj.model.EventCategory;
import hr.tvz.volontiraj.model.UserEntity;

import java.util.HashSet;

public class EventMapper {

    public static EventDto mapEventToEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setCategory(event.getCategory().toString());
        eventDto.setTitle(event.getTitle());
        eventDto.setDescription(event.getDescription());
        eventDto.setLocation(event.getLocation());
        eventDto.setStartDateTime(event.getStartDateTime());
        eventDto.setUpvote(event.getUpvote());

        if (event.getCreator() != null) {
            eventDto.setCreatorId(event.getCreator().getId());
        }
        eventDto.setVolunteerCount(event.getVolunteers().size());

        return eventDto;
    }

    public static Event mapEventDtoToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setCategory(EventCategory.valueOf(eventDto.getCategory()));
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setStartDateTime(eventDto.getStartDateTime());
        event.setUpvote(eventDto.getUpvote());


        if (eventDto.getCreatorId() != null) {
            UserEntity creator = new UserEntity();
            creator.setId(eventDto.getCreatorId());
            event.setCreator(creator);
        }


        event.setVolunteers(new HashSet<>());

        return event;
    }

}
