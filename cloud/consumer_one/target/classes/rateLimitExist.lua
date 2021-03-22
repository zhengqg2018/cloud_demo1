local result = 0
local value = redis.pcall("EXISTS", KEYS[1])
 if (value == 0) then
    result = 0
 else
    result = 1
end
return result