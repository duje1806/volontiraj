package hr.tvz.volontiraj.service;

import hr.tvz.volontiraj.model.EventCategory;
import hr.tvz.volontiraj.dto.EventDto;
import hr.tvz.volontiraj.filterParams.EventFilterParams;
import hr.tvz.volontiraj.mapper.EventMapper;
import hr.tvz.volontiraj.model.Event;
import hr.tvz.volontiraj.model.UserEntity;
import hr.tvz.volontiraj.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    public List<EventDto> findAllPagedAndFiltered(Pageable pageable, EventFilterParams filterParams) {
        // Za sada ignoriraj filterParams jer filteriranje ćeš ti dodati kasnije
        List<Event> events = eventRepository.findAll(pageable).getContent();
        return events.stream()
                .map(EventMapper::mapEventToEventDto)
                .collect(Collectors.toList());
    }

    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }

    public Event save(EventDto eventDto) {
        Event event = EventMapper.mapEventDtoToEvent(eventDto);
        if (eventDto.getCreatorId() != null) {
            UserEntity creator = userService.findById(eventDto.getCreatorId());
            event.setCreator(creator);
        }
        return eventRepository.save(event);
    }

    public Event update(Long id, EventDto eventDto) {
        Event existingEvent = findById(id);

        existingEvent.setCategory(eventDto.getCategory() != null
                ? EventCategory.valueOf(eventDto.getCategory())
                : existingEvent.getCategory());

        existingEvent.setTitle(eventDto.getTitle() != null ? eventDto.getTitle() : existingEvent.getTitle());
        existingEvent.setDescription(eventDto.getDescription() != null ? eventDto.getDescription() : existingEvent.getDescription());
        existingEvent.setLocation(eventDto.getLocation() != null ? eventDto.getLocation() : existingEvent.getLocation());
        existingEvent.setStartDateTime(eventDto.getStartDateTime() != null ? eventDto.getStartDateTime() : existingEvent.getStartDateTime());
        existingEvent.setUpvote(eventDto.getUpvote() != null ? eventDto.getUpvote() : existingEvent.getUpvote());

        // Ne diraj creator i volunteers jer se njima upravlja zasebno

        return eventRepository.save(existingEvent);
    }


    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
