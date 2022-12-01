Select c.name , sum(g.goals)
from Players p join Goals g on p.id = g.player_id join Countries c on c.id = g.country_id
group by g.country_id
order by sum(g.goals) Desc , c.country_id;