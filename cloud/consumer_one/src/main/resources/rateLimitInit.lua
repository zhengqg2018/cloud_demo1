local result = 1
do
redis.pcall("HMSET",KEYS[1],
            "last_mill_second",ARGV[1],
            "curr_permits",ARGV[2],
            "max_burst",ARGV[3],
            "rate",ARGV[4])
redis.pcall("Expire", KEYS[1], 60)
end
return result